package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.entity.business.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void save(Employee employee);

    public Employee getEmployeeById(Long id) throws NoEntityException;

    public Set<Employee> findAllByDepartmentName(String departmentName);
}
