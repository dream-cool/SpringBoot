package com.order.controller;

import com.order.enums.ResultEnum;
import com.order.pojo.Email;
import com.order.pojo.User;
import com.order.service.UserService;
import com.order.util.DataUtil;
import com.order.util.MailUtil;
import com.order.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Mrchen
 * @ Author   ：clt.
 * @ Date     ：Created in 12:46 2019/8/9
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/passwordLogin")
    public String loginPassword() {
        return "/login/PasswordLogin";
    }

    @RequestMapping("/verificationLogin")
    public String loginVerification() {
        return "/login/VerificationLogin";
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public ResultUtil unauthorized(){
        return ResultUtil.unauthorized("没有权限");
    }

    @RequestMapping("/session")
    @ResponseBody
    public ResultUtil session(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return ResultUtil.success(user);
    }

    @RequestMapping("/verificationLoginCheck")
    @ResponseBody
    public Map<String, String> verificationLoginCheck(@RequestBody User user) {
        Map<String, String> map = new HashMap<>(16);
        String code = null;
        String temp;
        String password = (String) template.opsForHash().get(user.getUserName(), "password");
        if (password != null) {
            temp = (String) template.opsForHash().get(user.getUserName(), "count");
            if (Integer.parseInt(temp) >= 2) {
                map.put("code", "400");
                template.delete(user.getUserName());
            } else if (user.getPassWord().equals(password)) {
                template.opsForValue().set(user.getUserName() + "check", "0");
                map.put("code", "100");
            } else {
                template.opsForHash().increment(user.getUserName(), "count", 1);
                map.put("code", "200");
            }
        } else {
            map.put("code", "404");
        }
        return map;
    }

    @RequestMapping("/sendVerificationLogin")
    @ResponseBody
    public Map<String, String> send(@RequestBody User user) {
        Random random = new Random();
        Map<String, String> rmap = new HashMap<>(16);
        Map<String, String> map = new HashMap<>(16);
        String content = "";
        int retime = DataUtil.remainingTime();
        for (int i = 0; i < 6; i++) {
            int temp = random.nextInt(10);
            content += temp;
        }
        String key = user.getUserName() + "check";
        String check = template.opsForValue().get(key);
        if (check != null) {
            if (Integer.parseInt(check) >= 3) {
                template.expire(key,  retime, TimeUnit.SECONDS);
                map.put("check", "200");
                return map;
            } else {
                template.opsForValue().increment(key);
            }
        } else {
            template.opsForValue().set(key, "1");
        }
        rmap.put("password", content);
        rmap.put("count", "0");
        template.opsForHash().putAll(user.getUserName(), rmap);
        mailUtil.sendSimpleMail(new Email(user.getUserName(), "验证码邮件", content));
        template.expire(user.getUserName(), 60, TimeUnit.SECONDS);
        map.put("code", "500");
        return map;
    }

    @RequestMapping("/passwordLoginCheck")
    @ResponseBody
    public Map<String,String> passwordLoginCheck(@RequestBody User user ){
        Map<String,String> map =new HashMap<>(16);
        String code="2000";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            map.put("code",ResultEnum.USER_SELECT_BY_USERNAME_FAILED.getCode().toString());
            map.put("message", ResultEnum.USER_SELECT_BY_USERNAME_FAILED.getMessage());
            return map;
        } catch (IncorrectCredentialsException e){
            map.put("code",ResultEnum.USER_UPDATE_PASSWORD_FAILED.getCode().toString());
            map.put("message", ResultEnum.USER_UPDATE_PASSWORD_FAILED.getMessage());
            return map;
        } catch (LockedAccountException e){
            map.put("code",ResultEnum.USER_IS_LOCKED.getCode().toString());
            map.put("message", ResultEnum.USER_IS_LOCKED.getMessage());
            return map;
        }
        User u = (User) subject.getPrincipal();
        userService.updateUserlastLoginTime(u.getUserId(), new Date());
        map.put("code",ResultEnum.USER_LOGIN_SUCCESS.getCode().toString());
        map.put("message",ResultEnum.USER_LOGIN_SUCCESS.getMessage());
        return map;
    }

    @RequestMapping("userInfo")
    @ResponseBody
    public ResultUtil roleCheck(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Map<String, User> map = new HashMap<>();
        if (!Objects.isNull(user)){
            map.put("user", user);
            return ResultUtil.success(map);
        } else {
            return ResultUtil.failed("没有登录的用户");
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResultUtil userLogout(){
        Subject subject = SecurityUtils.getSubject();
        final Object principal = subject.getPrincipal();
        if (!Objects.isNull(principal)){
            subject.logout();
            return ResultUtil.success(null);
        } else {
            return ResultUtil.failed("没有登录的用户");
        }
    }
}
