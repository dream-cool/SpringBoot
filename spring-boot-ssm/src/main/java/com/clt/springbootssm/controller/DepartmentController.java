package com.clt.springbootssm.controller;

import com.clt.springbootssm.pojo.Department;
import com.clt.springbootssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping("/seldep/{id}")
    public Department getDepById(@PathVariable("id") Integer id){
        Department department=departmentService.getDepById(id);
        return department;
    }

    @GetMapping("/deldep/{id}")
    public int delDepById(@PathVariable("id") Integer id){
        return  departmentService.delDepById(id);
    }

    @GetMapping("/upddep/{id}")
    public Department updDepById(@PathVariable("id") Integer id, Department department){
        int rows=departmentService.updDepById(id,department);
        return  departmentService.getDepById(id);
    }

    @GetMapping("/insdep")
    public Department insDepById(Department department){
        boolean rows= departmentService.addDepById(department);
        return  department;
    }

    @GetMapping("/t")
    public String test(Department department){
        return "fsa";
    }
}
