package com.stephen.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserLogicImplement implements UserLogic {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User signUp(User user) throws Exception {
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new Exception("Username already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public User login(Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        if(user.getPassword().equals(login.getPassword())){
            return user;
        }
        return null;
    }
}
