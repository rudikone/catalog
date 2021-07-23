package com.rudikov.catalog.service.abstr;

import com.rudikov.catalog.model.entity.business.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department save(Department department);

    Department getDepartmentById(Long id);

    void remove(Long id);

    Department findDepartmentByName(String name);
}
