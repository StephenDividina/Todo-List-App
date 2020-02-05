package com.stephen.user;

public interface UserLogic {
    User signUp(User user) throws Exception;
    User login(Login login);
}
