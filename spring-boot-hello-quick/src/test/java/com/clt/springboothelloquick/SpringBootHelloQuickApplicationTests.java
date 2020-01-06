package com.clt.springboothelloquick;

import org.hibernate.validator.constraints.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootHelloQuickApplicationTests {



    @Autowired
    private JavaMailSender javaMailSender;


    //接收人
    private static final String RECIPINET = "1769693979@qq.com";

    /**
     * 发送文本邮件
     */

    @Test
    public void contextLoads() {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("1142170725@qq.com");
        message.setSubject("测试邮件");
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        message.setText("这是一封测试邮件,发送时间为"+simpleDateFormat.format(new Date()));
        message.setTo(RECIPINET);
        long start =System.currentTimeMillis();
        javaMailSender.send(message);
        long end =System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Value("${spring.mail.username}")
    private String sender;

    @Test
    public  void time(){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(("这是一封测试邮件"+simpleDateFormat.format(new Date())));
        System.out.println(sender);

    }


}
