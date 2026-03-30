# Plan: End-to-End Testing with Playwright MCP

## TL;DR
The application has **no end-to-end tests**. Use the **Playwright MCP server** to let Copilot drive a real browser and verify the Task Tracker UI works correctly — creating, completing, and deleting tasks through the frontend. This demonstrates how MCP tooling extends Copilot beyond code generation into interactive testing.

## Prerequisites
- The app is running locally on `http://localhost:3000` (`./mvnw spring-boot:run`)
- The Playwright MCP server is configured in `.vscode/mcp.json` (already included in this project)
- **Node.js** and **Chrome** are installed (included automatically in the dev container)

## Steps

### Phase 1: Smoke Test — Page Loads
1. **Navigate to the app** — use Playwright to open `http://localhost:3000` and take a snapshot
2. **Verify the page title and heading** — confirm the Task Tracker UI renders correctly

### Phase 2: Create a Task
3. **Fill in the task form** — type a title (e.g., "Buy groceries") into the input field
4. **Submit the form** — click the add button
5. **Verify the task appears** — take a snapshot and confirm the new task is visible in the task list

### Phase 3: Complete a Task
6. **Toggle task completion** — click the checkbox or toggle button on the newly created task
7. **Verify the task is marked complete** — confirm the UI reflects the completed state (strikethrough, checkmark, etc.)

### Phase 4: Delete a Task
8. **Delete the task** — click the delete button on the task
9. **Verify the task is removed** — take a snapshot and confirm the task list is empty

### Phase 5: Error Handling
10. **Try submitting an empty form** — attempt to create a task with no title
11. **Verify validation** — confirm the UI shows an error or prevents submission

## Relevant Files
- `.vscode/mcp.json` — Playwright MCP server configuration (already included)
- `public/index.html` — the frontend being tested (read-only, for understanding selectors)

## Verification
1. Page loads at `http://localhost:3000` with the correct heading
2. A task can be created and appears in the list
3. A task can be toggled complete and the UI updates
4. A task can be deleted and disappears from the list
5. Empty form submission is handled gracefully

## Decisions
- **Playwright MCP** — uses a real browser, no test framework setup or dependencies in the project
- **Interactive testing via Copilot** — Agent Mode drives the browser through MCP tool calls; no test files to write or maintain
- **Snapshot-based verification** — use `browser_snapshot` to inspect the DOM state rather than screenshots, for reliable assertions

## Implementation
To implement this plan, switch to **Agent Mode** and ensure the Playwright MCP server is configured. Copilot will use MCP tools like `browser_navigate`, `browser_click`, `browser_type`, and `browser_snapshot` to drive the browser and verify each step. The **Extend with MCP Tooling** guide ([docs/06-mcp-tooling.md](docs/06-mcp-tooling.md)) covers how MCP servers work.

## Further Considerations
1. **Automated test scripts**: The interactive MCP approach is great for exploration and demos. For CI, consider generating Playwright test scripts (`*.spec.ts`) based on what was validated.
2. **Screenshot capture**: Use `browser_take_screenshot` for visual regression testing or documentation screenshots.
3. **Multiple browsers**: Playwright supports Chromium, Firefox, and WebKit — the MCP server can be configured to target different browsers.
