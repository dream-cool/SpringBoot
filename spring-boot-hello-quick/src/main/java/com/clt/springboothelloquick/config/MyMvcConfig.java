package com.clt.springboothelloquick.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
// super.addViewControllers(registry);
//浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/").setViewName("relogin");
        registry.addViewController("/index.html").setViewName("relogin");
    }
}
