package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    void save(Department department);
}
