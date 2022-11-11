package com.github.moinmarcell.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moinmarcell.backend.model.Todo;
import com.github.moinmarcell.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void getTodos() throws Exception {
        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void addTodo() throws Exception {

        MvcResult result = mvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                            "description": " ",
                                            "status": " "
                                        }
                                        """
                        ))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                   "description": " ",
                                   "status": " "
                                }
                                """
                ))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Todo todo = objectMapper.readValue(content, Todo.class);
        assertNotNull(todo.id());
    }

    @Test
    @DirtiesContext
    void getTodoById() throws Exception {
        todoRepo.todos().add(new Todo("1", " ", " "));

        mvc.perform(get("/api/todo/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                    "id": "1",
                                    "description": " ",
                                    "status": " "
                                }
                                """
                ));
    }

    @Test
    @DirtiesContext
    void editTodoById() throws Exception {
        todoRepo.todos().add(new Todo("1", " ", " "));

        mvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                            "id": "1",
                                            "description": "test",
                                            "status": "test"
                                        }
                                        """
                        )
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                    "id": "1",
                                    "description": "test",
                                    "status": "test"
                                }
                                """
                ));
    }

    @Test
    @DirtiesContext
    void deleteTodoById() throws Exception {
        todoRepo.todos().add(new Todo("1", " ", " "));

        mvc.perform(delete("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                    "id": "1",
                                    "description": " ",
                                    "status": " "
                                }
                                """
                ));
    }
}