package com.rudikov.catalog.service.abstr;

import com.rudikov.catalog.model.entity.business.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee save(Employee employee);

    public Employee getEmployeeById(Long id);

    public Set<Employee> findAllByDepartmentName(String departmentName);

    public void remove(Long id);
}
