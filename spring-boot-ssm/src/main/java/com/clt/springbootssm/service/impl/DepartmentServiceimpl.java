package com.clt.springbootssm.service.impl;

import com.clt.springbootssm.mapper.DepartmentMapper;
import com.clt.springbootssm.pojo.Department;
import com.clt.springbootssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "departmentService")
public class DepartmentServiceimpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department getDepById(Integer id) {
        return departmentMapper.getDepById(id);
    }

    @Override
    public int delDepById(Integer id) {
        return departmentMapper.delDepById(id);
    }

    @Override
    public int updDepById(Integer id, Department department) {
        return departmentMapper.updDepById(id,department);
    }

    @Override
    public boolean addDepById(Department department) {
        return departmentMapper.addDepById(department);
    }
}
