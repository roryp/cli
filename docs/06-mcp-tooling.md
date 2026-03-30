# 🔧 MCP Tooling — Extend Copilot's Reach

<img src="images/mcp-tooling.png" alt="MCP Tooling — Extend Copilot's Reach" width="800">

MCP (Model Context Protocol) gives GitHub Copilot access to **external tools and data sources** — turning it from a code assistant into a connected development partner.

## What Is MCP?

MCP is an open protocol that lets AI assistants interact with external servers. Each MCP server provides **tools** that Copilot can invoke from any chat mode:

- Read and write files with structured access
- Query databases
- Call APIs
- Fetch web content
- Interact with GitHub (issues, PRs, repos)
- And much more

## How It Works

1. You define MCP servers in `.vscode/mcp.json`
2. VS Code launches local servers on first use, and connects to remote servers automatically
3. Copilot discovers the available tools
4. Copilot calls these tools when needed — **MCP tools require Agent Mode**

> **Important:** MCP tools are only available in **Agent Mode**. Ask Mode and Plan Mode do not have access to MCP servers.

## This Project's MCP Configuration

Check out [`.vscode/mcp.json`](../.vscode/mcp.json):

```json
{
  "servers": {
    "filesystem": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "${workspaceFolder}"
      ]
    },
    "github": {
      "type": "http",
      "url": "https://api.githubcopilot.com/mcp/"
    },
    "playwright": {
      "command": "npx",
      "args": ["@playwright/mcp@latest"],
      "type": "stdio"
    },
    "microsoft-docs": {
      "url": "https://learn.microsoft.com/api/mcp"
    }
  }
}
```

| Server | Type | What It Does | Example Use |
|---|---|---|---|
| `filesystem` | Local (stdio) | Structured file access | "Read all controller files and summarize the API" |
| `github` | Remote (HTTP) | GitHub API access | "Create an issue for the missing validation bug" |
| `playwright` | Local (stdio) | Browser automation | "Navigate to localhost:3000 and test the UI" |
| `microsoft-docs` | Remote (HTTP) | Microsoft Learn search | "How does Spring Boot handle dependency injection?" |

## Try It — MCP in Action

Switch to **Agent Mode** to use MCP tools. They are not available in Ask or Plan mode.

### Interact with GitHub
```
Create a GitHub issue titled "Add task priority feature" with a description of the requirements
```
Copilot uses the `github` server to create the issue directly.

### File Analysis
```
Read all files in src/main/java/com/vibetracker/controller/ and generate an API documentation table
```
Copilot uses the `filesystem` server to read the files and produces structured output.

### Test the UI with Playwright
```
Navigate to http://localhost:3000, create a task called "Buy groceries", then verify it appears in the list
```
Copilot uses the `playwright` server to launch a browser, fill in the form, click Add, and take a snapshot to confirm the task was created. See [`PLAN.md`](../PLAN.md) for a full end-to-end testing plan you can execute.

### Search Microsoft Documentation
```
How does Spring Boot handle request validation?
```
Copilot uses the `microsoft-docs` server to search Microsoft Learn and ground its answer in official documentation.

## Adding Your Own MCP Servers

The MCP ecosystem is growing. You can add servers for:

- **Databases:** PostgreSQL, SQLite, MongoDB
- **Cloud:** AWS, Azure, GCP management
- **APIs:** Slack, Jira, Linear, Notion
- **DevOps:** Docker, Kubernetes, Terraform
- **Custom tools:** Build your own MCP server for internal tools

### Example: Adding a SQLite Server

```json
{
  "servers": {
    "sqlite": {
      "command": "npx",
      "args": ["-y", "@modelcontextprotocol/server-sqlite", "./data/tasks.db"]
    }
  }
}
```

## 💡 Tips

- **MCP tools require Agent Mode** — Ask and Plan modes do not have access to MCP servers.
- **Start small.** The filesystem and GitHub servers cover most needs.
- **Use environment variables** for secrets (API keys, tokens) — never hardcode them.
- **Check the MCP registry** for community-built servers: [github.com/modelcontextprotocol/servers](https://github.com/modelcontextprotocol/servers)

## What's Next?

Try executing the Playwright testing plan in [`PLAN.md`](../PLAN.md) — it walks through creating, completing, and deleting tasks via a real browser, all driven by Copilot through MCP.

Vibe code with structure, context, and control. 🎶
