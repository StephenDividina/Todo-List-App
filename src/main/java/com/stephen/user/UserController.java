package com.stephen.user;

import com.stephen.todo.TodoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    private static User user;

    @Autowired
    private UserLogic userLogic;

    @PostMapping("/signup")
    public User register(@RequestBody User user) throws Exception {
        return userLogic.signUp(user);
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
        TodoController.setUser(user);
        return "Logout successfully";
    }

}
