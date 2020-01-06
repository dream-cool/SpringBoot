package com.rabbitmq;

import com.rabbitmq.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestRabbitmqApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.direct"));
        System.out.println("交换机创建完成");

        amqpAdmin.declareQueue(new Queue("test.amqp"));
        System.out.println("队列创建完成");

        amqpAdmin.declareBinding( new Binding(
                "test.amqp", Binding.DestinationType.QUEUE,
                "amqpAdmin.direct","",null));
    }

    @Test
    public void contextLoads() {
//        rabbitTemplate.send(exchange,rountekey,message);
        Person person = new Person();
        person.setAge("11");
        person.setBirthday(new Date());
        person.setName("陈留涛");
        person.setSex("男");
        rabbitTemplate.convertAndSend("amqpAdmin.direct","",person);
    }

    @Test
    public void receiveTest(){
        Object o = rabbitTemplate.receiveAndConvert("test.amqp");
        System.out.println(o);
//        Object o1 = rabbitTemplate.receiveAndConvert("atclt.emps");
//        Object o2 = rabbitTemplate.receiveAndConvert("atclt");
//        Object o1 = rabbitTemplate.receiveAndConvert("atclt");
//        System.out.println(o.getClass());
//        System.out.println(o1);
//        System.out.println(o2);
    }

    @Test
    public void localDateTest(){
        System.out.println(LocalDate.now());
    }

}
