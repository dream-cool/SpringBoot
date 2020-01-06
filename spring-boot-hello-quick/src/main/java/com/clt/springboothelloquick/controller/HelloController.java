package com.clt.springboothelloquick.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String Hello(){
        return "world";
    }

    @ResponseBody
    @RequestMapping("/w")
    public String s(){
        return "world";
    }

}
