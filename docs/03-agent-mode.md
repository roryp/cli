# 🤖 Agent Mode — Execute Across Files and Tools

Agent mode is where the magic happens. Copilot doesn't just suggest code — it **makes changes** across multiple files, runs terminal commands, and iterates until the job is done.

## What Is Agent Mode?

Agent mode gives GitHub Copilot the ability to:
- **Edit multiple files** in a single operation
- **Create new files** and directories
- **Run terminal commands** (install packages, run tests, start servers)
- **Iterate on errors** — if something breaks, it reads the output and fixes it
- **Use tools** via MCP (Model Context Protocol) for extended capabilities

It's like handing your plan to a developer who can touch every part of the project.

## When to Use It

- You have a clear plan (from Plan mode) and want Copilot to implement it
- A change spans multiple files (model, routes, frontend, tests)
- You want to scaffold new features end-to-end
- You need to refactor code across the project

## Try It — Walkthrough

### Scenario: Add a search endpoint

1. **Switch to Agent mode** in Copilot Chat

2. **Give a clear prompt:**
   ```
   Add a GET /api/tasks/search endpoint that accepts a ?q= query parameter
   and returns tasks whose title or description contains the search term.
   Also add a search box to the frontend that calls this endpoint.
   ```

3. **Watch Copilot work** — It will:
   - Add a search method to `TaskRepository.java`
   - Add a new endpoint in `TaskController.java`
   - Update `public/index.html` with a search input
   - May run `mvn spring-boot:run` to verify the changes work

4. **Review the changes** — Copilot shows a diff for each file. Accept, reject, or ask for adjustments.

## Agent Mode Superpowers

| Capability | Example |
|---|---|
| Multi-file edits | Update model, routes, and frontend in one go |
| Terminal commands | `mvn compile`, `mvn test`, `mvn spring-boot:run` |
| Error recovery | Reads test failures and fixes the code |
| File creation | Scaffolds new modules, test files, configs |
| Tool use (MCP) | Query databases, call APIs, fetch documentation |

## 💡 Tips

- **Start with a plan.** Agent mode works best when you've already shaped the solution.
- **Be specific about scope.** "Add search to the API and frontend" is better than "Make the app better."
- **Review diffs carefully.** Agent mode is powerful but not infallible — always review.
- **Let it iterate.** If the first attempt has errors, Copilot will often fix them automatically.
- **Use `@workspace`** to give Copilot full context of your project.

## What Comes Next?

Now that you know the three modes, learn how to give Copilot even more context with [copilot-instructions.md](./04-copilot-instructions.md).
