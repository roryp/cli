# 🧪 Testing the Vibe Task Tracker

This guide explains how the Vibe Task Tracker is tested, where the tests live, and how to add new tests. It's written for learners who are new to Spring Boot testing.

---

## 📋 Overview

The project uses **JUnit 5** and **Spring Boot Test** to verify the backend API. Tests run against a real Spring application context with a simulated HTTP layer — no browser or live server needed.

There are two types of tests:

- **Smoke test** — confirms the app starts without errors
- **Integration tests** — send HTTP requests to the API and check the responses

---

## ▶️ Running the Tests

Use the Maven wrapper included in the project. No separate Maven installation is required.

**Run all tests:**

```bash
# Linux / macOS
./mvnw test

# Windows
mvnw.cmd test
```

**Run a single test class:**

```bash
# Linux / macOS
./mvnw test -Dtest=TaskControllerTests

# Windows
mvnw.cmd test -Dtest=TaskControllerTests
```

Maven will print a summary showing how many tests passed, failed, or were skipped.

💡 **Tip:** If you see a `BUILD SUCCESS` message, all tests passed. A `BUILD FAILURE` means at least one test failed — scroll up in the output to find the failing test name and the reason.

---

## 📁 Test Structure

Test files mirror the main source tree under `src/test/java/`. This makes it easy to find the test for any class — just follow the same package path.

```
src/
├── main/java/com/vibetracker/
│   ├── VibeTaskTrackerApplication.java
│   └── controller/
│       └── TaskController.java
│
└── test/java/com/vibetracker/
    ├── VibeTaskTrackerApplicationTests.java   ← smoke test for the app
    └── controller/
        └── TaskControllerTests.java           ← integration tests for TaskController
```

📝 **Note:** When you add a new class to `src/main/`, create its test file in the matching package under `src/test/`.

---

## 🗂️ Existing Tests

### `VibeTaskTrackerApplicationTests.java`

**Location:** `src/test/java/com/vibetracker/VibeTaskTrackerApplicationTests.java`  
**Purpose:** Verifies that the Spring application context loads without errors. This is the first test to run — if it fails, nothing else will work.

| Test method | What it checks |
|---|---|
| `contextLoads()` | The Spring Boot app starts up without throwing an exception |

---

### `TaskControllerTests.java`

**Location:** `src/test/java/com/vibetracker/controller/TaskControllerTests.java`  
**Purpose:** Integration tests for the Task REST API. Each test sends a simulated HTTP request and asserts the correct status code and response body.

| Test method | HTTP call | What it checks |
|---|---|---|
| `getAllTasks_returnsOk()` | `GET /api/tasks` | Returns `200 OK` with a JSON array |
| `createTask_withValidTitle_returnsCreated()` | `POST /api/tasks` with a title | Returns `201 Created` |
| `createTask_withoutTitle_returnsBadRequest()` | `POST /api/tasks` without a title | Returns `400 Bad Request` |
| `getTask_withInvalidId_returnsNotFound()` | `GET /api/tasks/{bad-id}` | Returns `404 Not Found` |
| `createAndToggleTask()` | `POST` then `PATCH /api/tasks/{id}/toggle` | The `completed` field flips from `false` to `true` |
| `deleteTask_withInvalidId_returnsNotFound()` | `DELETE /api/tasks/{bad-id}` | Returns `404 Not Found` |
| `createAndDeleteTask()` | `POST` then `DELETE /api/tasks/{id}` | Delete returns `204 No Content`; subsequent `GET` returns `404 Not Found` |

---

## ✍️ Writing New Tests

Follow these conventions when adding tests to the project.

### Annotations to use

```java
@SpringBootTest               // boots the full Spring application context
@AutoConfigureMockMvc         // sets up MockMvc for simulated HTTP requests
class YourNewTests {

    @Autowired
    private MockMvc mockMvc;  // use this to send requests in your tests
}
```

### How to use MockMvc

MockMvc lets you simulate HTTP requests without starting a real server. Here's the basic pattern:

```java
mockMvc.perform(get("/api/tasks"))         // build the request
       .andExpect(status().isOk())         // assert the status code
       .andExpect(jsonPath("$").isArray()); // assert something about the body
```

Common methods:

| MockMvc method | HTTP verb |
|---|---|
| `get("/path")` | GET |
| `post("/path")` | POST |
| `put("/path")` | PUT |
| `patch("/path")` | PATCH |
| `delete("/path")` | DELETE |

### Naming convention

Use the `methodName_scenario_expectedResult` pattern. This makes it immediately clear what each test does:

```
getAllTasks_returnsOk
createTask_withValidTitle_returnsCreated
createTask_withoutTitle_returnsBadRequest
```

⚠️ **Warning:** Each test should be independent. Don't rely on tasks created by a previous test — create your own data at the start of each test that needs it.

---

## 💡 Example: Adding a New Test

Suppose you want to test that updating a task with `PUT /api/tasks/{id}` returns `200 OK`. Here is how you would write that test:

```java
@Test
void updateTask_withValidId_returnsOk() throws Exception {
    // 1. Create a task first so we have a valid ID to update
    String createResponse = mockMvc.perform(
            post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Original title\"}"))
        .andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();

    // 2. Extract the ID from the response JSON
    String id = JsonPath.read(createResponse, "$.id");

    // 3. Send a PUT request with the updated title
    mockMvc.perform(
            put("/api/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated title\"}"))
        .andExpect(status().isOk())                          // assert 200 OK
        .andExpect(jsonPath("$.title").value("Updated title")); // assert new title
}
```

Walk-through of what happens at each step:

1. **Create** — POST a new task and capture the full response body as a string.
2. **Extract** — Pull the `id` field out of that JSON string using `JsonPath`.
3. **Update** — PUT to the task's URL and assert the response status and body.

📝 **Note:** Add `import com.jayway.jsonpath.JsonPath;` at the top of your test file to use `JsonPath.read()`. This library is included with Spring Boot Test.

---

## 🔗 Related Docs

- [Ask Mode guide](docs/01-ask-mode.md) — explore the codebase before writing tests
- [Plan Mode guide](docs/02-plan-mode.md) — plan a testing strategy with Copilot
- [Agent Mode guide](docs/03-agent-mode.md) — let Copilot generate and run tests for you
- [Copilot Instructions guide](docs/04-copilot-instructions.md) — teach Copilot your testing conventions
