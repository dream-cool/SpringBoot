package com.clt.springbootssm.service.impl;


import com.clt.springbootssm.mapper.EmployeeMapper;
import com.clt.springbootssm.pojo.Employee;
import com.clt.springbootssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value ="employeeService")
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = "emp" ,key = "#id")
    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    @CacheEvict(cacheNames = "emp" ,key = "#id")
    @Override
    public int delEmpById(Integer id) {
        return employeeMapper.delEmpById(id);
    }

    @CachePut(cacheNames = "emp" ,key = "#id")
    @Override
    public Employee updEmpById(Integer id, Employee employee) {
        employeeMapper.updEmpById(id,employee);
        return employee;
    }

    @CachePut(cacheNames = "emp" ,key = "#employee.lastName")
    @Override
    public Employee addEmpById(Employee employee) {
        employeeMapper.addEmpById(employee);
        return employee;
    }
}
