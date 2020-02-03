package com.stephen.todo;

import com.stephen.user.User;

import java.util.List;

public interface TodoLogic {
    void addTodo(Todo todo);
    List<Todo> getAllTodo(Integer userId);
    Todo updateTodo(Integer id, Todo todo);
    void deleteTodo(Integer id);
    List<Todo> getAllByDate();

}
