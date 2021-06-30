package com.rudikov.catalog.repository;

import com.rudikov.catalog.model.entity.Employee;
import com.rudikov.catalog.model.entity.Moderator;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    Optional<Employee> findById(Long id);

}
