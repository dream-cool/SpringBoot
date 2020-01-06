package com.springbootshiro.controller;

import com.springbootshiro.util.GetIP;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mrchen
 * @ Author   ：clt.
 * @ Date     ：Created in 9:53 2019/7/31
 */
@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloTest(HttpServletRequest servletRequest) {
        String ip = GetIP.getIpAddress(servletRequest);
        System.out.println("hello");
        System.out.println(ip);
        return "hello";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("name", "hello world");
        return "test";
    }

    @RequestMapping("/add")
    public String userAdd() {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String userUpdate() {
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String userToLogin(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        return "/user/login";
    }

    @RequestMapping("/login")
    public String userLogin(String username, String password, Model model){
        System.out.println(""+username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "/user/login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "/user/login";
        }
        model.addAttribute("msg", "登录成功");
        return "test";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public String userUnauthorized(){
        return "没有授权";
    }

    @RequestMapping("/logout")
    public String userLogout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/user/login";
    }
}
