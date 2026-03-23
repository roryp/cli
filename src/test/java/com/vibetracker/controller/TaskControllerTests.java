package com.vibetracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTests {

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void createTask_withoutTitle_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Missing title\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTask_withInvalidId_returnsNotFound() throws Exception {
        mockMvc.perform(get("/api/tasks/nonexistent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAndToggleTask() throws Exception {
        // Create a task
        String response = mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Toggle Me\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.completed").value(false))
                .andReturn().getResponse().getContentAsString();

        // Extract the id from the response
        String id = com.jayway.jsonpath.JsonPath.read(response, "$.id");

        // Toggle the task
        mockMvc.perform(patch("/api/tasks/" + id + "/toggle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deleteTask_withInvalidId_returnsNotFound() throws Exception {
        mockMvc.perform(delete("/api/tasks/nonexistent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAndDeleteTask() throws Exception {
        String response = mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Delete Me\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String id = com.jayway.jsonpath.JsonPath.read(response, "$.id");

        mockMvc.perform(delete("/api/tasks/" + id))
                .andExpect(status().isNoContent());

        // Verify it's gone
        mockMvc.perform(get("/api/tasks/" + id))
                .andExpect(status().isNotFound());
    }
}
