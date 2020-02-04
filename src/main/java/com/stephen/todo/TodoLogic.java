package com.stephen.todo;

import com.stephen.user.User;

import java.util.List;

public interface TodoLogic {
    void addTodo(Todo todo);
    List<Todo> getAllTodo(Integer userId);
    Todo updateTodo(Integer userId, Integer id, Integer vid, Todo todo);
    Boolean deleteTodo(Integer userId, Integer id);
    List<Todo> getAllByDate(Integer userId);
    public List<Todo> getAllByName(Integer userId);

}
