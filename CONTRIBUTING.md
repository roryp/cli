# Contributing to Vibe Task Tracker

Thanks for your interest in contributing! This is a hands-on workshop project
that demonstrates GitHub Copilot's vibe coding workflow — see the
[README](README.md) for the full overview, tech stack, and learning goals.

## Prerequisites

See the [Prerequisites](README.md#-prerequisites) section in the README. The
quickest path is to open the repo in the included dev container or a GitHub
Codespace — Java 21, Maven, and the GitHub CLI come pre-configured.

## Workflow

```bash
# 1. Fork the repo on GitHub, then clone your fork
git clone https://github.com/<your-username>/vibecoding.git
cd vibecoding

# 2. Create a focused feature branch
git checkout -b my-change

# 3. Make your changes and commit
git commit -am "Describe your change"

# 4. Push and open a pull request against main
git push origin my-change
```

## Build & Test

```bash
./mvnw test              # run the test suite
./mvnw spring-boot:run   # run the app on http://localhost:3000
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Pull Request Guidelines

- Keep PRs small and focused on a single change.
- Include a clear description of **what** changed and **why**.
- Make sure `./mvnw test` passes before requesting review.
- Follow the conventions in
  [`.github/copilot-instructions.md`](.github/copilot-instructions.md)
  (constructor injection, proper HTTP status codes, small focused methods, etc.).

## License & Conduct

This project is released under the [MIT License](README.md#-license). Please be
kind and respectful in issues, PRs, and discussions.
