package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.Employee;
import com.rudikov.catalog.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    @Lock(value = LockModeType.OPTIMISTIC)
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public void update(Employee employeeForUpdate, Employee newEmployee) {
        employeeForUpdate.setRank(newEmployee.getRank());
        employeeForUpdate.setPosition(newEmployee.getPosition());
        employeeForUpdate.setFirstName(newEmployee.getFirstName());
        employeeForUpdate.setLastName(newEmployee.getLastName());
        employeeForUpdate.setPhoneNumber(newEmployee.getPhoneNumber());
        employeeForUpdate.setDepartment(newEmployee.getDepartment());
        employeeRepo.save(employeeForUpdate);
    }


}
