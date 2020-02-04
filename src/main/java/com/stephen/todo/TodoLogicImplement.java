package com.stephen.todo;

import com.stephen.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Todo updateTodo(Integer userId, Integer id, Integer vid, Todo todo) {
        if(!todoRepository.findByUserIdAndId(userId, id).isEmpty()) {
            todo = todoRepository.findById(id).get();
            return todoRepository.save(todo);
        }
        return null;
//            Todo todo1 = todoRepository.findById(id).get();
//            if(!todo.getName().isEmpty()) {
//                todo1.setName(todo.getName());
//            }
//            if(!todo.getDescription().isEmpty()) {
//                todo1.setDescription(todo.getDescription());
//            }
//            todo1.setDate(todo.getDate());
//            todo1.setUser(todo.getUser());
//            todoRepository.save(todo1);
//            todo = todoRepository.findById(id).get();

//            todo = todoRepository.findById(id).get();
//            return todoRepository.save(todo);
//            return true;
//        }else{
//            return false;
//        }
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
