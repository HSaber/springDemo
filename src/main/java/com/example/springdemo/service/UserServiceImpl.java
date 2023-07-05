package com.example.springdemo.service;

import com.example.springdemo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public User getUserInfo(){
        User user=new User();
        user.setName("jack");
        user.setPassword(123123);
        return user;
    }
}
