# Copilot Instructions — Vibe Coding Workshop

## Project Overview
This is a **Vibe Task Tracker** — a Spring Boot REST API with a vanilla HTML/CSS/JS frontend. It is designed as a teaching project for a workshop on GitHub Copilot's vibe coding workflow.

## Tech Stack
- **Runtime:** Java 21+
- **Backend:** Spring Boot 4.0 (REST API)
- **Build:** Maven
- **Frontend:** Vanilla HTML, CSS, and JavaScript (no frameworks)
- **Data:** In-memory store (no database — keep it simple for the workshop)
- **IDs:** UUIDs via `java.util.UUID`

## Coding Conventions
- Use **constructor injection** for Spring beans — never field injection
- Use **records** or POJOs with explicit getters/setters for models
- Prefer **`final`** fields and local variables where possible
- Name files in **PascalCase** (e.g., `TaskController.java`)
- Name variables and methods in **camelCase**
- Name classes in **PascalCase**
- Keep methods small and focused — one responsibility per method
- Always return proper HTTP status codes (200, 201, 204, 400, 404, 500)
- Use **`@RestController`** and **`@RequestMapping`** for API endpoints
- Use **`ResponseStatusException`** for error responses

## API Design
- RESTful routes under `/api/tasks`
- JSON request and response bodies
- Validate required fields before processing
- Return consistent error shapes: `{ "error": "message" }`

## Error Handling
- Use a `@RestControllerAdvice` for centralized error handling
- Never expose stack traces in production responses
- Log errors for debugging

## Testing
- Use JUnit 5 and Spring Boot Test
- Test files live under `src/test/java/` mirroring the main source tree

## Frontend
- Keep it minimal — a single `index.html` with inline or linked CSS/JS
- Use `fetch()` for API calls
- No build step required

## Workshop Context
This project exists to teach vibe coding with GitHub Copilot. When generating code:
- Favor **clarity over cleverness** — this is for learners
- Add brief inline comments explaining *why*, not *what*
- Keep examples self-contained and easy to follow
