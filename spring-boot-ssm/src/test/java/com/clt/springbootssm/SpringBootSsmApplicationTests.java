package com.clt.springbootssm;

import com.clt.springbootssm.mapper.EmployeeMapper;
import com.clt.springbootssm.pojo.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSsmApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> empredisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Autowired
    EmployeeMapper employeeMapper;


    @Test
    public void contextLoads() {
    }



}
