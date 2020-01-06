package com.order.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ：clt
 * @Date ：Created in 10:04 2019/8/28
 */
public class RequsetUtil {
    public static void sessionInfo(HttpServletRequest request){
        System.out.println(request.getSession().getId());
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        System.out.println(request.getPathInfo());
        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());
        Cookie[] cookies = request.getCookies();
        if (!Objects.isNull(cookies)){
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
            }
        }

    }


}
