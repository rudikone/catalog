package com.rudikov.catalog.service.impl;

import com.rudikov.catalog.exception.NoFoundEntityException;
import com.rudikov.catalog.exception.NoSavedEntityException;
import com.rudikov.catalog.repository.DepartmentRepo;
import com.rudikov.catalog.repository.EmployeeRepo;
import com.rudikov.catalog.service.abstr.CheckEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckEntityServiceImpl implements CheckEntityService {

    private DepartmentRepo departmentRepo;
    private EmployeeRepo employeeRepo;

    @Autowired
    public CheckEntityServiceImpl(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void checkExistDepartmentById(Long id) {
        if (!departmentRepo.existsById(id)) {
            throw new NoFoundEntityException("Department с id=" + id + " не найден!");
        }
    }

    @Override
    public void checkExistDepartmentByName(String name) {
        if (!departmentRepo.existsByName(name)) {
            throw new NoFoundEntityException("Department с name=" + name + " не найден!");
        }
    }

    @Override
    public void checkExistEmployeeById(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new NoFoundEntityException("Employee с id=" + id + " не найден!");
        }
    }

    @Override
    public void checkNullableOrSavedEntity(Object o) {
        if (o == null) {
            throw new NoSavedEntityException("Ошибка сохранения " + o + " в базу данных!");
        }
    }
}
