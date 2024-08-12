package com.enigmacamp.todoapp.controller;

import com.enigmacamp.todoapp.entity.Todo;
import com.enigmacamp.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("/todo/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoService.getTodoById(id);
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @PutMapping("/todo")
    public void updateTodo(@RequestBody Todo todo) {
        todoService.updateTodo(todo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }
}
