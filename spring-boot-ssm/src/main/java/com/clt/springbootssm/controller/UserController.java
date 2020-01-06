package com.clt.springbootssm.controller;


import com.clt.springbootssm.pojo.User;
import com.clt.springbootssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Mrchen
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public User login( User user){
        User u= userService.selectLogin(user);
        return u==null ? null:u;
    }
}
