package com.rudikov.catalog.service.impl;

import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.repository.EmployeeRepo;
import com.rudikov.catalog.service.abstr.CheckEntityService;
import com.rudikov.catalog.service.abstr.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepo employeeRepo;
    private CheckEntityService checkEntityService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo, CheckEntityService checkEntityService) {
        this.employeeRepo = employeeRepo;
        this.checkEntityService = checkEntityService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        checkEntityService.checkExistEmployeeById(id);
        return employeeRepo.findById(id).get();
    }

    @Override
    public Set<Employee> findAllByDepartmentName(String departmentName) {
        checkEntityService.checkExistDepartmentByName(departmentName);
        return employeeRepo.findAllByDepartmentName(departmentName).get();
    }

    @Override
    public void remove(Long id) {
        checkEntityService.checkExistEmployeeById(id);
        employeeRepo.deleteById(id);
    }

}
