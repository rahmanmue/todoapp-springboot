package com.enigmacamp.todoapp.service;

import com.enigmacamp.todoapp.entity.Todo;

import java.util.List;

public interface TodoService {
    public void addTodo(Todo todo);

    public void deleteTodo(String id);

    public void updateTodo(Todo todo);

    public Todo getTodoById(String id);

    List<Todo> getAllTodo();
}
