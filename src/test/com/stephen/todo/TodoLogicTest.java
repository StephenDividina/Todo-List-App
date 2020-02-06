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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup(){
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void saveTodo(){
        Todo todo = new Todo();
        User user = new User();

        user.setName("stephen");
        user.setUsername("stephen");
        user.setPassword("12345");
        user = userRepository.save(user);
        todo.setUser(user);
        todo.setName("alarm");
        todo.setDescription("birthday");
        todo.setDate(new Date(07-02-2020));
        Todo todoSave = todoRepository.save(todo);


        Assert.assertEquals(todo.getUser().getId(), todoSave.getUser().getId());
        Assert.assertEquals(todo.getId(), todoSave.getId());
        Assert.assertEquals(todo.getName(), todoSave.getName());
        Assert.assertEquals(todo.getDescription(), todoSave.getDescription());
        Assert.assertEquals(todo.getDate(), todoSave.getDate());
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
        todoRepository.save(todo);
        List<Todo> todoList = todoLogic.getAllTodo(user.getId());
        Assert.assertEquals(1, todoList.size());
        Assert.assertEquals("aaa", todo.getName());

        List<Todo> todoList2 = todoLogic.getAllTodo(4234);
        Assert.assertEquals(0, todoList2.size());
    }

    @Test
    public void updateTodo() throws Exception {
        Todo todo = new Todo();
        User user = new User();

        user.setName("stephen");
        user.setUsername("stephen");
        user.setPassword("12345");
        user = userRepository.save(user);

        todo.setUser(user);
        todo.setName("alarm");
        todo.setDescription("birthday");
        todo.setDate(new Date(2020, 02, 06));
        todoRepository.save(todo);

        Todo todo1 = new Todo();
        todo1.setUser(user);
        todo1.setId(todo.getId());
        todo1.setName("New Alarm");
        todo1.setDescription("Cousin's Birthday");
        todo1.setDate(new Date(2020, 02, 07));
        todo1.setVersion(todo.getVersion());
        todoLogic.updateTodo(todo1);

        List<Todo> todoList = todoLogic.getAllTodo(user.getId());

        Todo todo2 = todoList.get(0);

        Assert.assertEquals(1, todoList.size());
        Assert.assertEquals(todo.getId(), todo2.getId());
        Assert.assertEquals(todo.getUser().getId(), todo2.getUser().getId());
        Assert.assertNotEquals(todo.getName(), todo2.getName());
        Assert.assertNotEquals(todo.getDescription(), todo2.getDescription());
        Assert.assertNotEquals(todo.getDate(), todo1.getDate());
    }

    @Test(expected = Exception.class)
    public void updateTodoWithNullPointer() throws Exception{
        Todo todo = new Todo();
        User user = new User();

        user.setName("stephen");
        user.setUsername("stephen");
        user.setPassword("12345");
        user = userRepository.save(user);

        todo.setUser(user);
        todo.setName("alarm");
        todo.setDescription("birthday");
        todo.setDate(new Date(2020, 02, 06));
        todoRepository.save(todo);

        Todo todo1 = new Todo();
        todo1.setUser(user);
        todo1.setId(2323);
        todo1.setName("New Alarm");
        todo1.setDescription("Cousin's Birthday");
        todo1.setDate(new Date(2020, 02, 07));
        todo1.setVersion(todo.getVersion());
        todoLogic.updateTodo(todo1);

    }

    @Test(expected = Exception.class)
    public void deleteTodoWithNullPointer() throws Exception {
        Todo todo = new Todo();
        User user = new User();

        user.setName("stephen");
        user.setUsername("stephen");
        user.setPassword("12345");
        user = userRepository.save(user);
        todo.setUser(user);
        todo.setName("aaaaa");
        todo.setDescription("xxx");
        todo.setDate(new Date(01-02-2020));
        todoRepository.save(todo);

        todoLogic.deleteTodo(1, 2);
    }

    @Test
    public void deleteTodo() throws Exception {
        Todo todo = new Todo();
        User user = new User();

        user.setName("stephen");
        user.setUsername("stephen");
        user.setPassword("12345");
        user = userRepository.save(user);
        todo.setUser(user);
        todo.setName("aaaaa");
        todo.setDescription("xxx");
        todo.setDate(new Date(01-02-2020));
        todoRepository.save(todo);

        Todo todo1 = new Todo();
        todo1.setUser(user);
        todo1.setName("bbbbb");
        todo1.setDescription("xzzx");
        todo1.setDate(new Date(01-03-2020));
        todoRepository.save(todo1);

        List<Todo> todoList = todoLogic.getAllTodo(user.getId());
        Assert.assertEquals(2, todoList.size());

        todoLogic.deleteTodo(user.getId(), todo1.getId());

        List<Todo> todoList2 = todoLogic.getAllTodo(user.getId());
        Assert.assertEquals(1, todoList2.size());
    }

    @Test
    public void getAllByName() {
        User user = new User();
        user.setName("asd");
        user.setPassword("Asd");
        user.setUsername("zxc");
        user = userRepository.save(user);

        Todo todo1 = new Todo();
        todo1.setUser(user);
        todo1.setName("x");
        todo1.setDescription("x");
        todo1.setDate(new Date());
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setUser(user);
        todo2.setName("a");
        todo2.setDescription("a");
        todo2.setDate(new Date());
        todoRepository.save(todo2);

        Todo todo3 = new Todo();
        todo3.setUser(user);
        todo3.setName("m");
        todo3.setDescription("m");
        todo3.setDate(new Date());
        todoRepository.save(todo3);

        Todo todo4 = new Todo();
        todo4.setUser(user);
        todo4.setName("c");
        todo4.setDescription("c");
        todo4.setDate(new Date());
        todoRepository.save(todo4);

        List<Todo> todoList = todoLogic.getAllByName(user.getId());

        Todo temp1 = todoList.get(0);
        Todo temp2 = todoList.get(1);
        Todo temp3 = todoList.get(2);
        Todo temp4 = todoList.get(3);

        Assert.assertEquals(2, temp1.getId());
        Assert.assertEquals("a", temp1.getName());

        Assert.assertEquals(4, temp2.getId());
        Assert.assertEquals("c", temp2.getName());

        Assert.assertEquals(3, temp3.getId());
        Assert.assertEquals("m", temp3.getName());

        Assert.assertEquals(1, temp4.getId());
        Assert.assertEquals("x", temp4.getName());

        Assert.assertEquals(4, todoList.size());

    }

    @Test
    public void getAllByDate() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        User user = new User();
        user.setName("asd");
        user.setPassword("Asd");
        user.setUsername("zxc");
        user = userRepository.save(user);

        Todo todo1 = new Todo();
        todo1.setUser(user);
        todo1.setName("x");
        todo1.setDescription("x");
        todo1.setDate(format.parse("10-02-2020"));
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setUser(user);
        todo2.setName("a");
        todo2.setDescription("a");
        todo2.setDate(format.parse("03-02-2020"));
        todoRepository.save(todo2);

        Todo todo3 = new Todo();
        todo3.setUser(user);
        todo3.setName("m");
        todo3.setDescription("m");
        todo3.setDate(format.parse("05-02-2020"));
        todoRepository.save(todo3);

        Todo todo4 = new Todo();
        todo4.setUser(user);
        todo4.setName("c");
        todo4.setDescription("c");
        todo4.setDate(format.parse("01-02-2020"));
        todoRepository.save(todo4);

        List<Todo> todoList = todoLogic.getAllByDate(user.getId());

        Todo temp1, temp2, temp3, temp4;

        temp1 = todoList.get(0);
        temp2 = todoList.get(1);
        temp3 = todoList.get(2);
        temp4 = todoList.get(3);

        Assert.assertEquals(4, todoList.size());

        Assert.assertEquals(todo4.getId(), temp1.getId());
        Assert.assertEquals(todo4.getName(), temp1.getName());
        Assert.assertEquals(todo4.getDate(), temp1.getDate());

        Assert.assertEquals(todo2.getId(), temp2.getId());
        Assert.assertEquals(todo2.getName(), temp2.getName());
        Assert.assertEquals(todo2.getDate(), temp2.getDate());

        Assert.assertEquals(todo3.getId(), temp3.getId());
        Assert.assertEquals(todo3.getName(), temp3.getName());
        Assert.assertEquals(todo3.getDate(), temp3.getDate());

        Assert.assertEquals(todo1.getId(), temp4.getId());
        Assert.assertEquals(todo1.getName(), temp4.getName());
        Assert.assertEquals(todo1.getDate(), temp4.getDate());

    }

}