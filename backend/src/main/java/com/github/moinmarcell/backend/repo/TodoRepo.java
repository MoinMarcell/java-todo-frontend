package com.github.moinmarcell.backend.repo;

import com.github.moinmarcell.backend.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepo {
    private List<Todo> todos;

    public List<Todo> todos() {
        return todos;
    }

    public Todo addTodo(Todo todoToAdd) {
        for (Todo t : todos) {
            if (t.equals(todoToAdd)) {
                return t;
            }
        }
        todos.add(todoToAdd);
        return todoToAdd;
    }

    public Todo getTodoById(String id){
        for(Todo t : todos){
            if(t.id().equals(id)){
                return t;
            }
        }
        return null;
    }

    public Todo editTodoById(Todo todoToEdit){
        for(Todo t : todos){
            if(t.id().equals(todoToEdit.id())){
                Todo copy = new Todo(todoToEdit.id(), todoToEdit.description(), todoToEdit.status());
                todos.remove(t);
                todos.add(copy);
                return copy;
            }
        }
        return null;
    }

    public Todo deleteTodoById(String id){
        for(Todo t : todos){
            if(t.id().equals(id)){
                todos.remove(t);
                return t;
            }
        }
        return null;
    }

}
