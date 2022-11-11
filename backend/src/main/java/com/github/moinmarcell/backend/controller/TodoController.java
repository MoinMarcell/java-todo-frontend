package com.github.moinmarcell.backend.controller;

import com.github.moinmarcell.backend.model.Todo;
import com.github.moinmarcell.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/todo")
    Todo addTodo(@RequestBody Todo todoToAdd){
        return todoService.addTodo(todoToAdd);
    }

    @GetMapping("/todo/{id}")
    Todo getTodoById(@PathVariable String id){
        return todoService.getTodoById(id);
    }

    @PutMapping("/todo/{id}")
    Todo editTodoById(@RequestBody Todo todoToEdit){
        return todoService.editTodoById(todoToEdit);
    }

    @DeleteMapping("/todo/{id}")
    Todo deleteTodoById(@PathVariable String id){
        return todoService.deleteTodoById(id);
    }

}
