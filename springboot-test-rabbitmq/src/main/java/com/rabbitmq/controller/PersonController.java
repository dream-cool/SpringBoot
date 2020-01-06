package com.rabbitmq.controller;

import com.rabbitmq.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 14:37 2019/6/26
 * @author Mrchen
 */
@Service
@RequestMapping("/test")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/show")
    public void showMessage(){
        personService.sendPerson();
    }

}
