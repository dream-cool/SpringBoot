package com.springbootshiro.mapper.mapper;

import com.springbootshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author Mrchen
 */
@Mapper
public interface UserMapper {

    /**
     *根据用户名查询用户
     */
    @Select("select * from user where username = #{userName} ")
    User findByUserName(String userName);

}
