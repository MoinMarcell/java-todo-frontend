package com.github.moinmarcell.backend.service;

import com.github.moinmarcell.backend.model.Todo;
import com.github.moinmarcell.backend.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    TodoRepo todoRepo;
    IdService idService;

    public TodoService(TodoRepo todoRepo, IdService idService) {
        this.todoRepo = todoRepo;
        this.idService = idService;
    }

    public List<Todo> getTodos(){
        return todoRepo.todos();
    }

    public Todo addTodo(Todo todoToAdd){
        Todo toSave = new Todo(idService.generateId(), todoToAdd.description(), todoToAdd.status());
        return todoRepo.addTodo(toSave);
    }

    public Todo getTodoById(String id){
        return todoRepo.getTodoById(id);
    }

    public Todo editTodoById(Todo todoToEdit){
        return todoRepo.editTodoById(todoToEdit);
    }

    public Todo deleteTodoById(String id){
        return todoRepo.deleteTodoById(id);
    }

}
