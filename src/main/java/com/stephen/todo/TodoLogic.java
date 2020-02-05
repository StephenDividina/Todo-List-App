package com.stephen.todo;

import java.util.List;

public interface TodoLogic {
    void addTodo(Todo todo);
    List<Todo> getAllTodo(Integer userId);
    Todo updateTodo(Todo todo);
    Boolean deleteTodo(Integer userId, Integer id);
    List<Todo> getAllByDate(Integer userId);
    List<Todo> getAllByName(Integer userId);

}
