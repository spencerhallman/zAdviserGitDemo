# GitHub Historical Data Backfill Utility

This utility extracts historical development activity data from GitHub repositories and sends it to an external analysis tool in the same format as webhooks.

## Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to the project directory:**
   ```bash
   cd <project-directory>
   ```
3. **Install the required dependencies:**
   ```bash
   pip install -r requirements.txt
   ```

## Usage

```bash
python github_backfill.py --github-token YOUR_TOKEN --repo owner/repo-name --start-date YYYY-MM-DD --end-date YYYY-MM-DD --webhook-url YOUR_WEBHOOK_URL
```

### Command-line Parameters

- `--github-token` (required): Your GitHub Personal Access Token.
- `--start-date` (required): The start date for data extraction in YYYY-MM-DD format.
- `--end-date` (required): The end date for data extraction in YYYY-MM-DD format.
- `--repo` (required): The repository in `owner/repo` format.
- `--events` (optional): A space-separated list of specific event types to extract. Defaults to all supported event types.
- `--webhook-url` (optional): The URL to send the webhook events to. If not provided, the script will run in dry-run mode.
- `--dry-run` (optional): A flag to preview the data without sending it to the webhook URL.

### Example Commands

- **Extract all events for a specific repository:**
  ```bash
  python github_backfill.py --github-token YOUR_TOKEN --repo owner/repo-name --start-date 2024-01-01 --end-date 2024-12-31 --webhook-url https://your-webhook-url.com/
  ```

- **Extract only specific event types:**
  ```bash
  python github_backfill.py --github-token YOUR_TOKEN --repo owner/repo-name --start-date 2024-01-01 --end-date 2024-12-31 --events push pull_request issues --webhook-url https://your-webhook-url.com/
  ```

- **Dry run to preview what would be extracted:**
  ```bash
  python github_backfill.py --github-token YOUR_TOKEN --repo owner/repo-name --start-date 2024-01-01 --end-date 2024-12-31 --dry-run
  ```
