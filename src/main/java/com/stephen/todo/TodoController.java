package com.stephen.todo;


import com.stephen.user.User;
import com.stephen.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/todo")
public class TodoController {

    private static User user;

    @Autowired
    private TodoLogic todoLogic;

    @GetMapping("/all")
    public List<Todo> getAllTodo(){
        Integer userId = user.getId();
        return todoLogic.getAllTodo(userId);
    }

    @GetMapping("/all/date")
    public List<Todo> getAllByDate(){
        Integer userId = user.getId();
        return todoLogic.getAllByDate(userId);
    }

    @GetMapping("/all/name")
    public List<Todo> getAllByName(){
        Integer userId = user.getId();
        return todoLogic.getAllByName(userId);
    }

    @PostMapping("/add")
    public String addTodo(@RequestBody Todo todo) {
        String msg;
        try {
            Integer userId = user.getId();
            todo.setUser(new User(userId, "", ""));
            todoLogic.addTodo(todo);
            msg = "Saved successfully";
        }
        catch (NullPointerException e){
            msg = "Please Login!";
        }
        return msg;
    }

    @PostMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo){
        todo.setUser(user);
        return todoLogic.updateTodo(todo);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id){
        Integer userId = user.getId();
        if(todoLogic.deleteTodo(userId, id) == true){
            return "Delete successfully";
        }else{
            return "Cannot find value of the Id";
        }
    }

    public static void setUser(User user) {
        TodoController.user = user;
    }
}
