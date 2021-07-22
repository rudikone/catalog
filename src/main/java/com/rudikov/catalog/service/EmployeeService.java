package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.entity.business.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void save(Employee employee);

    public Employee getEmployeeById(Long id) throws NoEntityException;

    public void update(Employee employeeForUpdate, Employee newEmployee);
}
