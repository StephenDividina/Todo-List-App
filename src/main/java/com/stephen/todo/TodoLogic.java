package com.stephen.todo;

import java.util.List;

public interface TodoLogic {
    Todo addTodo(Todo todo);
    List<Todo> getAllTodo(Integer userId);
    Todo updateTodo(Todo todo) throws Exception;
    void deleteTodo(Integer userId, Integer id) throws Exception;
    List<Todo> getAllByDate(Integer userId);
    List<Todo> getAllByName(Integer userId);

}
