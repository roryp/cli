# 🧪 Testing — Validate Your Code with Confidence

<img src="images/testing.png" alt="Testing — Validate Your Code with Confidence" width="800">

Testing ensures your API behaves exactly as expected — before a bug ever reaches a user. This project comes with a ready-to-run test suite, and Copilot can help you grow it.

## What Is Testing in This Project?

The project uses three standard tools to verify the backend:

| Tool | Role |
|---|---|
| **JUnit 5** | Runs individual test methods and reports pass/fail |
| **Spring Boot Test** | Starts an application context for realistic, end-to-end style tests |
| **MockMvc** | Simulates HTTP requests to the API without a running server |

Together they form **integration tests** — tests that exercise the full request-to-response cycle through your Spring Boot controllers.

Test files live in `src/test/java/com/vibetracker/`:

| File | What It Does |
|---|---|
| `VibeTaskTrackerApplicationTests.java` | Smoke test — confirms the application context loads without errors |
| `controller/TaskControllerTests.java` | Integration tests — covers all REST endpoints (GET, POST, PUT, PATCH, DELETE) |

## Running the Tests

Open a terminal and run:

**Linux / macOS:**
```bash
./mvnw test
```

**Windows:**
```cmd
mvnw.cmd test
```

You'll see output like this:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.vibetracker.VibeTaskTrackerApplicationTests
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.vibetracker.controller.TaskControllerTests
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] Results:
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

> 💡 **Tip:** Run the tests after every change. A green suite means you haven't broken anything that was already working.

## What's Already Tested

Here's what `TaskControllerTests.java` covers:

| Test | Endpoint | What It Verifies |
|---|---|---|
| `getAllTasks_returnsOk` | `GET /api/tasks` | Returns HTTP 200 with a JSON body |
| `createTask_withValidTitle_returnsCreated` | `POST /api/tasks` | Returns HTTP 201; new task has the right title and `completed: false` |
| `createTask_withoutTitle_returnsBadRequest` | `POST /api/tasks` | Returns HTTP 400 when title is missing |
| `getTask_withInvalidId_returnsNotFound` | `GET /api/tasks/{id}` | Returns HTTP 404 for an unknown ID |
| `createAndToggleTask` | `PATCH /api/tasks/{id}` | Creates a task then toggles its `completed` status |
| `deleteTask_withInvalidId_returnsNotFound` | `DELETE /api/tasks/{id}` | Returns HTTP 404 when deleting a non-existent task |
| `createAndDeleteTask` | `DELETE /api/tasks/{id}` | Creates a task then deletes it successfully |

Two of these tests are good examples to read closely:

```java
@Test
void getAllTasks_returnsOk() throws Exception {
    mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
}

@Test
void createTask_withValidTitle_returnsCreated() throws Exception {
    mockMvc.perform(post("/api/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\": \"Test Task\", \"description\": \"A test\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("Test Task"))
            .andExpect(jsonPath("$.completed").value(false));
}
```

The pattern is always the same: **perform** a request, then **expect** specific status codes and response body values.

## Try It — Use Copilot to Write Tests

### Scenario: Test the `PUT /api/tasks/{id}` update endpoint

The `PUT` endpoint lets users replace a task's title and description. Let's ask Copilot (in Agent mode) to write a test for it.

1. **Switch to Agent mode** — Select **Agent** from the mode dropdown at the top of the Copilot Chat panel

2. **Give Copilot a focused prompt:**
   ```
   In TaskControllerTests.java, add a test called updateTask_withValidData_returnsOk.
   It should create a task via POST /api/tasks, then send a PUT request to /api/tasks/{id}
   with a new title and description, and assert that the response is HTTP 200 and the
   returned title matches the updated value.
   ```

3. **Review what Copilot produces** — It should add a test that looks like this:

   ```java
   @Test
   void updateTask_withValidData_returnsOk() throws Exception {
       // Create a task first
       String response = mockMvc.perform(post("/api/tasks")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"title\": \"Original Title\", \"description\": \"Original\"}"))
               .andExpect(status().isCreated())
               .andReturn().getResponse().getContentAsString();

       // Extract the generated UUID (same pattern used in existing tests)
       String id = com.jayway.jsonpath.JsonPath.read(response, "$.id");

       // Update the task
       mockMvc.perform(put("/api/tasks/" + id)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"title\": \"Updated Title\", \"description\": \"Updated\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Updated Title"));
   }
   ```

4. **Run the tests** to confirm everything still passes:
   ```bash
   ./mvnw test
   ```

> ⚠️ **Warning:** If your test creates data but the test order is unpredictable, shared state can cause failures. Each test in this project uses an in-memory store — run them independently and avoid relying on state left by a previous test.

## Testing Tips with Copilot

### Use Ask Mode to understand what to test
Before writing a test, ask Copilot what edge cases matter:
```
What are the edge cases I should test for the PUT /api/tasks/{id} endpoint?
```

### Use Plan Mode to outline a test suite
Get a full list of test cases before generating any code:
```
List all the test cases we should have for the TaskController. Group them
by endpoint and include happy paths and error paths.
```

### Use Agent Mode to generate the tests
Once you know what to write, let Copilot do the typing:
```
Add the test cases we just outlined to TaskControllerTests.java
```

### More tips

- **Name tests clearly.** `createTask_withoutTitle_returnsBadRequest` is far easier to debug than `test3`.
- **Test one thing per test.** If a test fails, you want to know exactly why.
- **Cover the sad paths.** Missing input, bad IDs, and invalid data are just as important as the happy path.
- **Let Copilot explain existing tests.** In Ask mode, paste a test and ask "What is this test checking and why?"

## 💡 Tips

- **Run tests frequently.** `./mvnw test` is fast — make it a habit after every change.
- **Read failure messages carefully.** MockMvc output tells you exactly what status code or body value was wrong.
- **Ask Copilot to fix failing tests.** Paste the error output into the chat — Copilot can diagnose and fix most failures.
- **Add tests before you refactor.** A passing test suite is your safety net when changing existing code.
- **Use the smoke test as a baseline.** If `VibeTaskTrackerApplicationTests` fails, the app can't even start — fix that first.

## What Comes Next?

You've now covered the full workshop:

1. **Ask mode** → Explore and understand
2. **Plan mode** → Design the solution
3. **Agent mode** → Execute the changes
4. **copilot-instructions.md** → Project-wide conventions
5. **Agent profiles** → Role-specific expertise
6. **MCP tooling** → External tools and data
7. **Testing** → Validate what you've built

Head back to the [README](../README.md) for the complete workshop overview and to explore what to build next.
