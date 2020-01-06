package com.springbootshiro.service.service;

import com.springbootshiro.mapper.mapper.UserMapper;
import com.springbootshiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 18:54 2019/8/6
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String userName){
        return (User) userMapper.findByUserName(userName);
    }
}
