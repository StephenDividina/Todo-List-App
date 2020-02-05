package com.stephen.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoLogicImplement implements TodoLogic {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo(Integer userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    public List<Todo> getAllByDate(Integer userId) {
        return todoRepository.findByUserIdOrderByDateAsc(userId);
    }

    @Override
    public List<Todo> getAllByName(Integer userId) {
        return todoRepository.findByUserIdOrderByNameAsc(userId);
    }

    @Override
    public Todo updateTodo(Todo todo) {
        if (!todoRepository.findByUserIdAndId(todo.getUser().getId(), todo.getId()).isEmpty()) {
            return todoRepository.save(todo);
        }
        return null;
    }

    @Override
    public void deleteTodo(Integer userId, Integer id) throws Exception {
        if (!todoRepository.findByUserIdAndId(userId, id).isEmpty()) {
            todoRepository.deleteById(id);
        } else {
            throw new Exception("User cannot delete");
        }

    }


}
