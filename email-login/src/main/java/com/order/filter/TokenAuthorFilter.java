package com.order.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author ：clt
 * @Date ：Created in 18:27 2019/8/25
 */

@Component
@WebFilter(urlPatterns = "/**", filterName = "monitorFilter")
public class TokenAuthorFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //允许跨域,不能放在postHandle内
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Origin", "http://192.168.56.1:8080");
        System.out.println("#####"+request.getRemoteAddr());
        HttpSession session1 =  request.getSession();
        System.out.println("#####"+session1.getId());

        Cookie[] cookies = request.getCookies();
        if (!Objects.isNull(cookies)){
            for (Cookie cookie : cookies) {
                System.out.println("cookie name"+cookie.getName());
                System.out.println("cookie value1"+cookie.getValue());
            }
        }
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "1800");

        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,Accept");
        }
        filterChain.doFilter(request, response);
    }
}
