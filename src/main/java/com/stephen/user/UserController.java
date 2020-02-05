package com.stephen.user;

import com.stephen.todo.Todo;
import com.stephen.todo.TodoController;
import com.stephen.todo.TodoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
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
            TodoController.setUser(user);
            return "Login Successfully";
        }
    }

    @PostMapping("/logout")
    public String logout(){
        user = null;
        return "Logout successfully";
    }

//    @GetMapping("/user/todo")
//    public List<Todo> getAllTodo(){
//        Integer userId = user.getId();
//        return todoLogic.getAllTodo(userId);
//    }
//
//    @GetMapping("/user/todo/date")
//    public List<Todo> getAllByDate(){
//        Integer userId = user.getId();
//        return todoLogic.getAllByDate(userId);
//    }
//
//    @GetMapping("/user/todo/name")
//    public List<Todo> getAllByName(){
//        Integer userId = user.getId();
//        return todoLogic.getAllByName(userId);
//    }
//
//    @PostMapping("/user/todo")
//    public String addTodo(@RequestBody Todo todo) {
//        String msg;
//        try {
//                Integer userId = user.getId();
//                todo.setUser(new User(userId, "", ""));
//                todoLogic.addTodo(todo);
//                msg = "Saved successfully";
//        }
//        catch (NullPointerException e){
//            msg = "Please Login!";
//        }
//        return msg;
//    }
//
//    @PostMapping("/user/todo/update")
//    public Todo updateTodo(@RequestBody Todo todo){
//        todo.setUser(user);
//        return todoLogic.updateTodo(todo);
//    }
//
//    @DeleteMapping("/user/todo/{id}")
//    public String deleteTodo(@PathVariable Integer id){
//        Integer userId = user.getId();
//        if(todoLogic.deleteTodo(userId, id)){
//            return "Delete successfully";
//        }else{
//            return "Cannot find value of the Id";
//        }
//    }

}
