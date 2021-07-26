package com.rudikov.catalog.service.impl;

import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.repository.DepartmentRepo;
import com.rudikov.catalog.service.abstr.CheckEntityService;
import com.rudikov.catalog.service.abstr.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepo departmentRepo;
    private CheckEntityService checkEntityService;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, CheckEntityService checkEntityService) {
        this.departmentRepo = departmentRepo;
        this.checkEntityService = checkEntityService;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepo.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        checkEntityService.checkExistDepartmentById(id);
        return departmentRepo.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        checkEntityService.checkExistDepartmentById(id);
        departmentRepo.deleteById(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        checkEntityService.checkExistDepartmentByName(name);
        return departmentRepo.findDepartmentByName(name).get();
    }


}
