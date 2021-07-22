package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NotFoundDepartmentException;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    @Override
    public Department getDepartmentById(Long id) throws NotFoundDepartmentException {
        return departmentRepo.findById(id).orElseThrow(() -> new NotFoundDepartmentException(id));
    }

    @Override
    public Department update(Long id, String name) throws NotFoundDepartmentException {
        Department departmentFromDb = departmentRepo.findById(id).orElseThrow(() -> new NotFoundDepartmentException(id));
        departmentFromDb.setName(name);
        departmentRepo.save(departmentFromDb);
        return departmentFromDb;
    }

    @Override
    public void remove(Long id) {
        departmentRepo.deleteById(id);
    }


}
