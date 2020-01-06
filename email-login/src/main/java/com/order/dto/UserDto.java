package com.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author ：clt
 * @Date ：Created in 22:15 2019/8/14
 */
@Data
@Getter
@Setter
@ToString
public class UserDto {
    private String userName;
    private String sex;
    private String isVip;
    private String state;
    private String role;
    private String address;
    private String reg;
    private String lastLogin;
    private Long regTime;
    private Long lastLoginTime;
    private String message;
    private Integer pageSize;
    private Integer currentPage;

}
