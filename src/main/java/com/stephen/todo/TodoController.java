package com.stephen.todo;


import com.stephen.user.User;
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
    public Todo addTodo(@RequestBody Todo todo) throws Exception {
        if(user==null){
            throw new Exception("Please login.");
        }
        Integer userId = user.getId();
        todo.setUser(new User(userId, "", ""));
        return todoLogic.addTodo(todo);
    }

    @PostMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo) throws Exception {
        todo.setUser(user);
        return todoLogic.updateTodo(todo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable Integer id) throws Exception {
        Integer userId = user.getId();
        todoLogic.deleteTodo(userId, id);
    }

    public static void setUser(User user) {
        TodoController.user = user;
    }
}
