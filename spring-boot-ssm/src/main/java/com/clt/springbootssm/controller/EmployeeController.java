package com.clt.springbootssm.controller;

import com.clt.springbootssm.pojo.Employee;
import com.clt.springbootssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    static int i = 0;

    @RequestMapping("/selemp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        if ((i ^ 30) == 0 ) {
            System.out.println();
        }
        synchronized (this){
            System.out.print(++i+"\t");
        }
        Employee employee=employeeService.getEmpById(id);
        return employee;
    }

    @GetMapping("/delemp/{id}")
    public int delEmpById(@PathVariable("id") Integer id){
        return  employeeService.delEmpById(id);
    }

    @PostMapping("/updemp/{id}")
    public Employee updEmpById(@PathVariable("id") Integer id,Employee employee){
        employeeService.updEmpById(id,employee);
        return  employeeService.getEmpById(id);
    }

    @GetMapping("/insemp")
    public Employee insEmpById(Employee employee){
        employeeService.addEmpById(employee);
        return  employee;
    }


}
