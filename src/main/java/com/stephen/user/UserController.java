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

}
