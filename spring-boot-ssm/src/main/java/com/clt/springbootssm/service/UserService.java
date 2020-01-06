package com.clt.springbootssm.service;

import com.clt.springbootssm.pojo.User;

public interface UserService {
    User selectLogin(User user);
}
