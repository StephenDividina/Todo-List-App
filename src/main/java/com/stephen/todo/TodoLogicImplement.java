package com.stephen.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoLogicImplement implements TodoLogic{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo(Integer userId) {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findByUserId(userId)
                .forEach(todos::add);

        return todos;
    }

    @Override
    public List<Todo> getAllByDate(Integer userId){
        List<Todo> todos = new ArrayList<>();
        todoRepository.findByUserIdOrderByDateAsc(userId)
                .forEach(todos::add);

            return todos;
    }

    @Override
    public List<Todo> getAllByName(Integer userId){
        List<Todo> todos = new ArrayList<>();
        todoRepository.findByUserIdOrderByNameAsc(userId)
                .forEach(todos::add);

        return todos;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        if(!todoRepository.findByUserIdAndId(todo.getUser().getId(), todo.getId()).isEmpty()) {
            return todoRepository.save(todo);
        }
        return null;
    }

    @Override
    public Boolean deleteTodo(Integer userId, Integer id) {
        if(!todoRepository.findByUserIdAndId(userId, id).isEmpty()){
            todoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }


}
