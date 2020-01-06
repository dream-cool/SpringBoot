package com.clt.springbootssm.service;

import com.clt.springbootssm.pojo.Department;

public interface DepartmentService {
    Department getDepById(Integer id);

    int delDepById(Integer id);

    int updDepById(Integer id, Department department);

    boolean addDepById(Department department);
}
