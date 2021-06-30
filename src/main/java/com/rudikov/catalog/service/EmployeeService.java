package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void save(Employee employee);

    public Employee getEmployeeById(Long id);

    public void update(Employee employeeForUpdate, Employee newEmployee);
}
