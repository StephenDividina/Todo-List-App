package com.stephen.user;

import com.stephen.todo.Todo;
import com.stephen.todo.TodoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserController {

    private static User user;

    @Autowired
    private UserLogic userLogic;

    @Autowired
    private TodoLogic todoLogic;

    @PostMapping("/signup")
    public String register(@RequestBody SignUp signUp) {
        try {
            return userLogic.signUp(signUp);
        }catch (DataIntegrityViolationException e){
            return "Username Already Exist!";
        }

    }

    @PostMapping("/login")
    public String login(@RequestBody Login login){
        user = userLogic.login(login);

        if(user == null){
            return "Invalid Email or Password";
        }
        else{
            return "Login Successfully";
        }
    }

    @PostMapping("/logout")
    public String logout(){
        user = null;
        return "Logout successfully";
    }

    @GetMapping("/user/todo")
    public List<Todo> getAllTodo(){
        Integer userId = user.getId();
        return todoLogic.getAllTodo(userId);
    }

    @GetMapping("/user/todo/date")
    public List<Todo> getAllByDate(){
        Integer userId = user.getId();
        return todoLogic.getAllByDate();
    }

    @PostMapping("/user/todo")
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

    @PostMapping("/user/todo/{id}")
    public Todo updateTodo(@RequestBody Todo todo, @PathVariable Integer id){
        Integer userId = user.getId();
        todo.setUser(new User(userId, "", ""));
        return todoLogic.updateTodo(id, todo);
    }

    @DeleteMapping("/user/todo/{id}")
    public String deleteTodo(@PathVariable Integer id){
        todoLogic.deleteTodo(id);
        return "Delete successfully";
    }

}