# 🔧 MCP Tooling — Extend Copilot's Reach

MCP (Model Context Protocol) gives GitHub Copilot access to **external tools and data sources** — turning it from a code assistant into a connected development partner.

## What Is MCP?

MCP is an open protocol that lets AI assistants interact with external servers. Each MCP server provides **tools** that Copilot can invoke during Agent mode:

- Read and write files with structured access
- Query databases
- Call APIs
- Fetch web content
- Interact with GitHub (issues, PRs, repos)
- And much more

## How It Works

1. You define MCP servers in `.vscode/mcp.json`
2. VS Code starts the servers when you open the project
3. Copilot discovers the available tools
4. In Agent mode, Copilot calls these tools when needed

## This Project's MCP Configuration

Check out [`.vscode/mcp.json`](../.vscode/mcp.json):

```json
{
  "servers": {
    "filesystem": { ... },  // Read/write project files
    "github": { ... },      // Interact with GitHub
    "fetch": { ... }        // Fetch web content
  }
}
```

| Server | What It Does | Example Use |
|---|---|---|
| `filesystem` | Structured file access | "Read all route files and summarize the API" |
| `github` | GitHub API access | "Create an issue for the missing validation bug" |
| `fetch` | Web content retrieval | "Look up the Express.js docs for error handling" |

## Try It — MCP in Action

### Fetch Documentation
```
Look up the Node.js docs for the built-in test runner and summarize the key features
```
Copilot uses the `fetch` server to get the docs and provides a summary.

### Interact with GitHub
```
Create a GitHub issue titled "Add task priority feature" with a description of the requirements
```
Copilot uses the `github` server to create the issue directly.

### File Analysis
```
Read all files in src/routes/ and generate an API documentation table
```
Copilot uses the `filesystem` server to read the files and produces structured output.

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
      "args": ["-y", "@anthropic-ai/mcp-sqlite-server", "./data/tasks.db"],
      "description": "Query the task database"
    }
  }
}
```

## 💡 Tips

- **Start small.** The filesystem, GitHub, and fetch servers cover most needs.
- **Use environment variables** for secrets (API keys, tokens) — never hardcode them.
- **MCP tools work in Agent mode.** Ask mode and Plan mode don't invoke tools.
- **Check the MCP registry** for community-built servers: [github.com/modelcontextprotocol/servers](https://github.com/modelcontextprotocol/servers)

## The Complete Workflow

You now have the full picture:

1. **Ask mode** → Explore and understand
2. **Plan mode** → Design the solution
3. **Agent mode** → Execute the changes
4. **copilot-instructions.md** → Project-wide conventions
5. **Agent profiles** → Role-specific expertise
6. **MCP tooling** → External tools and data

Vibe code with structure, context, and control. 🎶
