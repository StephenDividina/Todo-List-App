package com.stephen.todo;

import com.stephen.user.User;
import com.stephen.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TodoLogicTest {

    @Autowired
    private TodoLogic todoLogic;

    @Autowired
    private TodoRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){
        repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getAllTodo() {
        Todo todo = new Todo();
        User user = new User();
        user.setName("asd");
        user.setPassword("Asd");
        user.setUsername("zxc");
        user = userRepository.save(user);
        todo.setUser(user);
        todo.setName("aaa");
        todo.setDescription("xxx");
        todo.setDate(new Date());
        repository.save(todo);
        List<Todo> todoList = todoLogic.getAllTodo(user.getId());
        Assert.assertEquals(1, todoList.size());
        Assert.assertEquals("aaa", todo.getName());

        List<Todo> todoList2 = todoLogic.getAllTodo(4234);
        Assert.assertEquals(0, todoList2.size());

    }
}