package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NotFoundDepartmentException;
import com.rudikov.catalog.model.entity.business.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    void save(Department department);

    Department getDepartmentById(Long id) throws NotFoundDepartmentException;

    void remove(Long id);

    Department findDepartmentByName(String name);
}
