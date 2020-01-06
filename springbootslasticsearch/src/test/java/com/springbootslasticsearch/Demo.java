package com.springbootslasticsearch;

import org.junit.Test;

public class Demo {
    @Test
    public void hashTest(){
        String str="你好啊";
        String test="你好啊";
        System.out.println(test.hashCode());
        System.out.println(test==str);
        System.out.println(str.hashCode());
    }
}
