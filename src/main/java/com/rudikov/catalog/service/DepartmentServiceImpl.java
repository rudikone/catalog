package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Department;
import com.rudikov.catalog.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public void save(Department department) {
        departmentRepo.save(department);
    }
}
