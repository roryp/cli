# Vibe Task Tracker — Recreation Prompt

Copy the prompt below into GitHub Copilot Agent mode to recreate this project from scratch.

---

Build a **Vibe Task Tracker** — a workshop project showing how GitHub Copilot turns an idea into a practical workflow: Ask mode to explore, Plan mode to shape the solution, and Agent mode to execute across files and tools. We'll also cover `copilot-instructions.md`, reusable `.agent.md` profiles, and MCP tooling — so you can move fast, stay grounded, and vibe code with more structure, context, and control.

**Backend:** Java 21 / Spring Boot 4.0 / Maven (include wrapper). `Task` POJO with UUID string `id`, `title`, `description` (optional, default `""`), `completed` (default false), `createdAt`/`updatedAt` (Instant). Store tasks in a `@Repository` backed by `ConcurrentHashMap` — no database. Use constructor injection everywhere, `ResponseStatusException` for errors, and a `@RestControllerAdvice` `GlobalExceptionHandler` that returns `{ "error": "..." }` and never exposes stack traces.

**API (`/api/tasks`):** GET (list, with optional `?completed=true|false` filter, sorted by createdAt desc), GET `/{id}`, POST (title required), PUT `/{id}` (partial update — only update fields present in the body), PATCH `/{id}/toggle` (flip completed), DELETE `/{id}` (204). Also add `GET /api/health` (status + timestamp) and a `GET /favicon.ico` → `/favicon.svg` redirect.

**Frontend (`public/index.html`):** Single file with inline `<style>` and `<script>`. Dark GitHub-inspired theme using CSS custom properties (`--bg: #0d1117`, `--surface: #161b22`, `--accent: #58a6ff`). Add-task form, All/Active/Completed filter tabs, task list with checkbox toggle + delete button (✕), strikethrough on completed, "X of Y completed" status bar, empty state, error state. Use `fetch()`, event delegation, DOM-based `escapeHtml()` for XSS protection, ARIA labels, `aria-live="polite"`, semantic HTML5, responsive max-width 640px.

**Tests:** JUnit 5 + `@SpringBootTest` + `@AutoConfigureMockMvc` + MockMvc. Test: list returns 200, create with valid title returns 201, create without title returns 400, get non-existent ID returns 404, create-then-toggle flips completed, delete non-existent returns 404, create-then-delete returns 204 then 404. Plus a `contextLoads()` smoke test.

**Copilot config:** `.github/copilot-instructions.md` covering tech stack, conventions (constructor injection, PascalCase/camelCase, small methods, proper HTTP status codes, `ResponseStatusException`, `@RestControllerAdvice`), API design, error handling, testing, and frontend patterns. Four `.github/agents/*.agent.md` with YAML frontmatter (`description`, `tools`) + guidelines + example interaction: **api-dev** (owns backend, tools: read/edit/search/execute), **frontend-dev** (owns `public/`, same tools), **code-reviewer** (read-only, tools: read/search, review checklist with severity levels), **docs-writer** (owns `docs/` + README, tools: read/edit/search).

Run on port 3000 with `spring.web.resources.static-locations=file:public/`. Build the project and make sure `./mvnw test` passes.
