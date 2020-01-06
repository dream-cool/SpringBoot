package com.clt.springbootssm.service;

import com.clt.springbootssm.pojo.Employee;


public interface EmployeeService {

    Employee getEmpById(Integer id);

    int delEmpById(Integer id);

    Employee updEmpById(Integer id, Employee employee);

    Employee addEmpById(Employee employee);
}
