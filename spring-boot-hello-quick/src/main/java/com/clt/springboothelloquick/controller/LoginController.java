package com.clt.springboothelloquick.controller;

import com.clt.springboothelloquick.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @RequestMapping("/check")
    @ResponseBody
    public Map<String ,String > loginCheck(@RequestBody  User user){
        Jedis jedis=null;
        Map<String ,String> map=new HashMap<>();
        String code=null;
        try {
            jedis=new Jedis("192.168.71.128",6379);
            String temp;
            String password=jedis.hget(user.getUserName(),"password");
            if(password!=null){
                temp=jedis.hget(user.getUserName(),"count");
                if(Integer.parseInt(temp)>=2){
                    map.put("code","400");
                    jedis.del(user.getUserName());
                }else if(user.getPassWord().equals(password)){
                    jedis.set(user.getUserName()+"check","0");
                    map.put("code","100");
                }else {
                    jedis.hincrBy(user.getUserName(),"count",1);
                    map.put("code","200");
                }
            }else {
                map.put("code","404");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return map;
    }



    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,String > send(@RequestBody User user){
        System.out.println(user);
        Jedis jedis=null;
        Random random=new Random();
        Map<String ,String > rmap=new HashMap<>();
        Map<String ,String > map=new HashMap<>();
        Date date=new Date();
        try{
            jedis=new Jedis("192.168.71.128",6379);
            String content="";
            long retime=(24-date.getHours())*3600-(date.getMinutes()*60)-(date.getSeconds());
            for (int i=0;i<6;i++){
                int temp=random.nextInt(10);
                content+=temp;
            }
            String key=user.getUserName()+"check";
            String check=jedis.get(key);
            if(check!=null){
               if(Integer.parseInt(check)>=3){
                   jedis.expire(key,(int) retime);
                   map.put("check","200");
                   return map;
               }else {
                   jedis.incr(key);
               }
            }else{
                jedis.set(key,"1");
            }
            rmap.put("password",content);
            rmap.put("count","0");
            jedis.hmset(user.getUserName(),rmap);

            SimpleMailMessage message=new SimpleMailMessage();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            message.setTo(user.getUserName());
            message.setText("您的邮箱验证码为："+content+"有效时间为一分钟，请尽快登录,发送时间为"+simpleDateFormat.format(new Date()));
            message.setFrom(sender);
            message.setSubject("邮箱验证");
            mailSender.send(message);
            jedis.expire(user.getUserName(),60);
            map.put("code","500");
            System.out.println("发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/world")
    public String Hello(){
        return "world";
    }
}
