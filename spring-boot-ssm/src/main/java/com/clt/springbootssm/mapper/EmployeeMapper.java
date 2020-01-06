package com.clt.springbootssm.mapper;


import com.clt.springbootssm.pojo.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from  employee where id=#{id} ")
    Employee getEmpById(Integer id);

    @Delete("delete from employee where id=#{id} ")
    int delEmpById(Integer id);

    @Update("update employee set lastName=#{employee.lastName} where id=#{id} ")
    boolean updEmpById(Integer id, Employee employee);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert  into employee(lastName, email, gender, d_id) values(#{lastName} ,#{email} ,#{gender} ,#{dId} )")
    boolean addEmpById(Employee employee);

}
