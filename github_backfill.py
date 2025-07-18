import argparse
import sys
import requests
import time

class GitHubClient:
    """A client for interacting with the GitHub API."""

    def __init__(self, token):
        """Initializes the GitHub client."""
        self.token = token
        self.headers = {
            "Authorization": f"token {self.token}",
            "Accept": "application/vnd.github.v3+json",
        }
        self.session = requests.Session()

    def get(self, url, params=None):
        """Sends a GET request to the GitHub API."""
        while True:
            response = self.session.get(url, headers=self.headers, params=params)
            if response.status_code == 403 and "rate limit exceeded" in response.text.lower():
                reset_time = int(response.headers.get("X-RateLimit-Reset", 0))
                sleep_duration = max(reset_time - time.time(), 0) + 10  # Add a 10-second buffer
                print(f"Rate limit exceeded. Sleeping for {sleep_duration:.0f} seconds.")
                time.sleep(sleep_duration)
                continue
            response.raise_for_status()
            return response

    def get_repo_events(self, repo, start_date, end_date, events):
        """Fetches repository events from the GitHub API."""
        url = f"https://api.github.com/repos/{repo}/events"
        params = {"per_page": 100}

        all_events = []

        while url:
            response = self.get(url, params=params)
            events_page = response.json()
            all_events.extend(events_page)

            if 'next' in response.links:
                url = response.links['next']['url']
            else:
                url = None

        return all_events


def transform_event(event):
    """
    Transforms a GitHub API event to the webhook format.
    TODO: Implement the actual transformation logic based on the webhook format.
    """
    return event["payload"]


def send_webhook(url, event):
    """Sends an event to the specified webhook URL."""
    try:
        response = requests.post(url, json=event, headers={"Content-Type": "application/json"})
        response.raise_for_status()
        print(f"Successfully sent event {event.get('id')} to {url}")
    except requests.exceptions.RequestException as e:
        print(f"Error sending event {event.get('id')} to {url}: {e}")


def main():
    """Main function."""
    parser = argparse.ArgumentParser(description="GitHub Historical Data Backfill Utility.")
    parser.add_argument("--github-token", required=True, help="GitHub Personal Access Token.")
    parser.add_argument("--start-date", required=True, help="Start date for data extraction (YYYY-MM-DD format).")
    parser.add_argument("--end-date", required=True, help="End date for data extraction (YYYY-MM-DD format).")
    parser.add_argument("--repo", required=True, help="Repository in owner/repo format.")
    parser.add_argument(
        "--events",
        nargs="+",
        default=["create", "deployment_status", "deployment", "issues", "pull_request_review", "pull_request", "push", "workflow_run"],
        help="Specific event types to extract.",
    )
    parser.add_argument("--dry-run", action="store_true", help="Preview mode without sending data.")
    parser.add_argument("--webhook-url", help="The URL to send the webhook events to.")

    args = parser.parse_args()

    print("GitHub Historical Data Backfill Utility")
    print("=======================================")
    print(f"Repository: {args.repo}")
    print(f"Start Date: {args.start_date}")
    print(f"End Date: {args.end_date}")
    print(f"Events: {', '.join(args.events)}")
    print(f"Dry Run: {args.dry_run}")

    from datetime import datetime

    start_date = datetime.strptime(args.start_date, "%Y-%m-%d")
    end_date = datetime.strptime(args.end_date, "%Y-%m-%d")

    client = GitHubClient(args.github_token)
    events = client.get_repo_events(args.repo, args.start_date, args.end_date, args.events)

    filtered_events = [
        event
        for event in events
        if start_date <= datetime.strptime(event["created_at"], "%Y-%m-%dT%H:%M:%SZ") <= end_date
        and event["type"] in args.events
    ]

    print(f"\nFound {len(filtered_events)} events.")

    for event in filtered_events:
        transformed_event = transform_event(event)
        if args.dry_run:
            print(f"  - [DRY RUN] Event: {event['type']} ({event['id']}) at {event['created_at']}")
            # print(json.dumps(transformed_event, indent=2))
        else:
            if args.webhook_url:
                send_webhook(args.webhook_url, transformed_event)
            else:
                print(f"  - [SKIPPING] Event: {event['type']} ({event['id']}) at {event['created_at']} (no webhook URL provided)")

if __name__ == "__main__":
    import json
    main()
