# Copilot Instructions — Vibe Coding Workshop

## Project Overview
This is a **Vibe Task Tracker** — a lightweight Node.js/Express REST API with a vanilla HTML/CSS/JS frontend. It is designed as a teaching project for a workshop on GitHub Copilot's vibe coding workflow.

## Tech Stack
- **Runtime:** Node.js 20+
- **Backend:** Express.js (REST API)
- **Frontend:** Vanilla HTML, CSS, and JavaScript (no frameworks)
- **Data:** In-memory store (no database — keep it simple for the workshop)
- **IDs:** UUIDs via the `uuid` package

## Coding Conventions
- Use **ES Module** syntax (`import`/`export`), not CommonJS (`require`)
- Use **`const`** by default; use `let` only when reassignment is needed; never use `var`
- Prefer **arrow functions** for callbacks and short functions
- Use **async/await** over raw Promises
- Name files in **kebab-case** (e.g., `task-routes.js`)
- Name variables and functions in **camelCase**
- Name classes in **PascalCase**
- Keep functions small and focused — one responsibility per function
- Always return proper HTTP status codes (200, 201, 204, 400, 404, 500)
- Include JSDoc comments on exported functions

## API Design
- RESTful routes under `/api/tasks`
- JSON request and response bodies
- Validate required fields before processing
- Return consistent error shapes: `{ "error": "message" }`

## Error Handling
- Use a centralized error-handling middleware
- Never expose stack traces in production responses
- Log errors to the console for debugging

## Testing
- Use Node.js built-in test runner (`node --test`)
- Test files live next to the code they test, named `*.test.js`

## Frontend
- Keep it minimal — a single `index.html` with inline or linked CSS/JS
- Use `fetch()` for API calls
- No build step required

## Workshop Context
This project exists to teach vibe coding with GitHub Copilot. When generating code:
- Favor **clarity over cleverness** — this is for learners
- Add brief inline comments explaining *why*, not *what*
- Keep examples self-contained and easy to follow
