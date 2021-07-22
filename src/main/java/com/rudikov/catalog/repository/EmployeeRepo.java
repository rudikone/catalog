package com.rudikov.catalog.repository;

import com.rudikov.catalog.model.entity.business.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    Optional<Set<Employee>> findAllByDepartmentName(String departmentName);
}
