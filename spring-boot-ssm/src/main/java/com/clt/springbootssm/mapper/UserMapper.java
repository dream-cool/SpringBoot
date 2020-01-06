package com.clt.springbootssm.mapper;

import com.clt.springbootssm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from mybatis.user  where username=#{username} and password=#{password}   ")
    User selectLogin(User user);
}
