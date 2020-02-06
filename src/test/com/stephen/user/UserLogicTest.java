package com.stephen.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserLogicTest {

    @Autowired
    private UserLogic userLogic;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void signUp() throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setName("j");
        user.setPassword("12345");
        User savedUser = userLogic.signUp(user);
        Assert.assertNotNull(savedUser.getId());
        Assert.assertEquals("user", savedUser.getUsername());
        Assert.assertEquals("j", savedUser.getName());
        Assert.assertEquals("12345", savedUser.getPassword());
    }

    @Test(expected = Exception.class)
    public void signUpWithDuplicateUsername() throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setName("j");
        user.setPassword("12345");
        User savedUser = userLogic.signUp(user);
        User user2 = new User();
        user2.setUsername("user");
        user2.setName("x");
        user2.setPassword("54321");
        User savedUser2 = userLogic.signUp(user);
    }

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setName("j");
        user.setPassword("12345");
        User savedUser = userLogic.signUp(user);
        Login login = new Login();
        login.setUsername("user");
        login.setPassword("54321");
        User userLogin = userLogic.login(login);
        Assert.assertNull(userLogin);
    }
}