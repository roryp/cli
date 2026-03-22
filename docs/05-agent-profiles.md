# 🧑‍💻 Agent Profiles (.agent.md) — Reusable Roles for Copilot

Agent profiles let you define **specialized roles** that Copilot can assume. Instead of one generic assistant, you get focused experts for different parts of your workflow.

## What Are Agent Profiles?

`.agent.md` files are Markdown documents that describe a persona with:
- A **role** (what they own)
- **Guidelines** (how they work)
- **Examples** (what good output looks like)

Place them in `.github/agents/` and invoke them by name in Copilot Chat.

## This Project's Agents

| Agent | File | Specialty |
|---|---|---|
| `@api-dev` | `.github/agents/api-dev.agent.md` | Backend API development |
| `@frontend-dev` | `.github/agents/frontend-dev.agent.md` | UI with vanilla HTML/CSS/JS |
| `@code-reviewer` | `.github/agents/code-reviewer.agent.md` | Code review and quality checks |
| `@docs-writer` | `.github/agents/docs-writer.agent.md` | Documentation and guides |

## Try It — Use an Agent

### Ask the API Developer
```
@api-dev Add a PATCH endpoint to update task priority
```
The response will follow REST conventions, use the in-memory store, and apply Spring Boot patterns — because that's what the `api-dev` profile specifies.

### Ask the Code Reviewer
```
@code-reviewer Review the task controller for any issues
```
You'll get structured feedback with severity levels (🔴🟡🟢), focused on correctness and security.

### Ask the Frontend Developer
```
@frontend-dev Add a dark/light theme toggle to the UI
```
The response will use CSS custom properties, semantic HTML, and no external dependencies.

### Ask the Docs Writer
```
@docs-writer Write a guide for the new priority feature
```
You'll get beginner-friendly documentation with examples, callouts, and clear structure.

## Anatomy of an Agent Profile

Here's the structure of a good `.agent.md` file:

```markdown
# Agent Name

One-sentence description of who this agent is.

## Role
What this agent owns and is responsible for.

## Guidelines
- Specific rules and conventions to follow
- What to do and what to avoid
- Quality standards

## Example Interaction
**User:** "Example prompt"
**You:** Description of expected behavior
```

## 💡 Tips

- **Keep agents focused.** One role per agent — don't make a "do everything" agent.
- **Include examples.** They anchor the agent's behavior better than rules alone.
- **Reference project files.** Tell agents which directories and files they own.
- **Combine with copilot-instructions.md.** The instructions file sets project-wide rules; agents add role-specific depth.
- **Create agents for your workflow.** Test writer? DevOps engineer? Security auditor? Make agents for them.

## What Comes Next?

Learn how to extend Copilot's capabilities beyond code with [MCP Tooling](./06-mcp-tooling.md).
