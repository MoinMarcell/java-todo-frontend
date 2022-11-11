package com.github.moinmarcell.backend.service;

import com.github.moinmarcell.backend.model.Todo;
import com.github.moinmarcell.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TodoServiceTest {
    TodoRepo todoRepo = mock(TodoRepo.class);
    IdService idService = mock(IdService.class);

    TodoService todoService = new TodoService(todoRepo, idService);

    @Test
    void getTodos_getEmptyListWhenGetTodosIsCalled() {
        // GIVEN
        List<Todo> expected = new ArrayList<>();
        // WHEN
        when(todoRepo.todos()).thenReturn(expected);
        List<Todo> actual = todoService.getTodos();
        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).todos();
    }

    @Test
    void addTodo() {
        // GIVEN
        String expectedId = "1";
        Todo expected = new Todo("1", " ", " ");
        // WHEN
        when(todoRepo.addTodo(any())).thenReturn(expected);
        when(idService.generateId()).thenReturn(expectedId);

        Todo actual = todoService.addTodo(expected);
        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).addTodo(expected);
    }

    @Test
    void getTodoById() {
        // GIVEN
        Todo expected = new Todo("1", " ", " ");
        String id = "1";
        // WHEN
        when(todoRepo.getTodoById(id)).thenReturn(expected);
        Todo actual = todoRepo.getTodoById(id);
        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).getTodoById(id);
    }

    @Test
    void editTodoById() {
        // GIVEN
        Todo expected = new Todo("1", " ", " ");
        // WHEN
        when(todoRepo.editTodoById(expected)).thenReturn(expected);
        Todo actual = todoService.editTodoById(expected);
        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).editTodoById(expected);
    }

    @Test
    void deleteTodoById() {
        // GIVEN
        Todo expected = new Todo("1", " ", " ");
        String id = "1";
        // WHEN
        when(todoRepo.deleteTodoById(id)).thenReturn(expected);
        Todo actual = todoService.deleteTodoById(id);
        // THEN
        assertEquals(expected, actual);
        verify(todoRepo).deleteTodoById(id);
    }
}