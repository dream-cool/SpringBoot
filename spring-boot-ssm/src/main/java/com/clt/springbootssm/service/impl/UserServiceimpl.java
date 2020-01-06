package com.clt.springbootssm.service.impl;

import com.clt.springbootssm.mapper.UserMapper;
import com.clt.springbootssm.pojo.User;
import com.clt.springbootssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectLogin(User user) {
        return userMapper.selectLogin(user);
    }
}
