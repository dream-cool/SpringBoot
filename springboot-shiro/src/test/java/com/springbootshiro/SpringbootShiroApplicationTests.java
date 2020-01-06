package com.springbootshiro;

import com.springbootshiro.util.GenSalt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroApplicationTests {

    @Test
    public void contextLoads() {
        GenSalt.main(null);
    }

    @Test
    public void test1(){
        String str = "sadsa123fsa123";
        System.out.println(str.indexOf("54"));
    }

}
