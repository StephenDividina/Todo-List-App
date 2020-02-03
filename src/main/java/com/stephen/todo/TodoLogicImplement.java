package com.stephen.todo;

import com.stephen.user.User;
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
    public List<Todo> getAllByDate(){
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAllByOrderByDateAsc().forEach(todos::add);

        return todos;
    }

    @Override
    public Todo updateTodo(Integer id, Todo todo) {
        Todo todo1 = todoRepository.findById(id).get();
        todo1.setName(todo.getName());
        todo1.setDescription(todo.getDescription());
        todo1.setDate(todo.getDate());
        todo1.setUser(todo.getUser());
        return todoRepository.save(todo1);
    }

    @Override
    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }


}
