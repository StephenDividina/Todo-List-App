package com.stephen.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserLogicImplement implements UserLogic {

    @Autowired
    private UserRepository userRepository;


    @Override
    public String signUp(SignUp signUp) {
        User user = new User();
        BeanUtils.copyProperties(signUp, user);
        userRepository.save(user);
        return "Registered Successfully";
    }

    @Override
    public User login(Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        return user;
    }
}
