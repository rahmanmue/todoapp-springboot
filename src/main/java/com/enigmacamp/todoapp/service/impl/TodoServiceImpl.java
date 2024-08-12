package com.enigmacamp.todoapp.service.impl;

import com.enigmacamp.todoapp.entity.Todo;
import com.enigmacamp.todoapp.repository.TodoRepository;
import com.enigmacamp.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(String id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        if(todoRepository.findById(todo.getId()).isPresent()){
            todoRepository.save(todo);
        }else{
            throw new RuntimeException("Todo not found");
        }
    }

    @Override
    public Todo getTodoById(String id) {
        return todoRepository.findById(id).get();
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }
}
