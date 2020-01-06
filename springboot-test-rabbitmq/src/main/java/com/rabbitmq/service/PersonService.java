package com.rabbitmq.service;

import com.rabbitmq.pojo.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author Mrchen
 */
@Service
public class PersonService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPerson() {
        int i = 0;
        Person person = new Person();
        while (true){
            person.setAge(""+System.currentTimeMillis());
            person.setBirthday(new Date());
            person.setName("陈留涛");
            person.setSex("男");
            rabbitTemplate.convertAndSend("amqpAdmin.direct","",person);
            System.out.println(i);
            System.out.println("发送了");
            i++;
            System.out.println(i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = "test.amqp")
    public void receivePerson(Person person){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收到第消息"+person);
    }



    @RabbitListener(queues = "atclt")
    public void receive(Person person){
        System.out.println("收到消息"+person);
    }

    @RabbitListener(queues = "atclt.news")
    public void receiveNews(Person person){
        System.out.println("收到消息"+person);
    }


    @RabbitListener(queues = "atclt.emps")
    public void receiveMessage(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
