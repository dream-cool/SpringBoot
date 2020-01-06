package com.clt.springbootssm.mapper;


import com.clt.springbootssm.pojo.Department;
import org.apache.ibatis.annotations.*;

import javax.crypto.KeyGenerator;

@Mapper
public interface DepartmentMapper {

    @Select("select  * from department where id=#{id} ")
    Department getDepById(Integer id);

    @Delete("delete from department where  id=#{id} ")
    int delDepById(Integer id);

    @Update("update department set departmentName=#{department.departmentName} where id=#{id} ")
    int updDepById(Integer id, Department department);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values (#{departmentName} )")
    boolean addDepById(Department department);

}
