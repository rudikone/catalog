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

    private final String NOT_FOUND_TEXT = " не найден!";

    @Autowired
    public CheckEntityServiceImpl(DepartmentRepo departmentRepo, EmployeeRepo employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void checkExistDepartmentById(Long id) {
        if (!departmentRepo.existsById(id)) {
            throw new NoFoundEntityException("Department с id=" + id + NOT_FOUND_TEXT);
        }
    }

    @Override
    public void checkExistDepartmentByName(String name) {
        if (Boolean.FALSE.equals(departmentRepo.existsByName(name))) {
            throw new NoFoundEntityException("Department с name=" + name + NOT_FOUND_TEXT);
        }
    }

    @Override
    public void checkExistEmployeeById(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new NoFoundEntityException("Employee с id=" + id + NOT_FOUND_TEXT);
        }
    }

    @Override
    public void checkNullableOrSavedEntity(Object o) {
        if (o == null) {
            throw new NoSavedEntityException("Ошибка сохранения в базу данных!");
        }
    }
}
