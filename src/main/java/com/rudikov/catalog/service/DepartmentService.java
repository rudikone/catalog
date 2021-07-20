package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.entity.Department;
import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    void save(Department department);

    Department getDepartmentById(Long id) throws NoEntityException;

    Department update(Long id, String name) throws NoEntityException;

    void remove(Long id);
}
