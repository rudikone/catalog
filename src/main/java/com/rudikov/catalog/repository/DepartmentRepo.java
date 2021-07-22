package com.rudikov.catalog.repository;

import com.rudikov.catalog.model.entity.business.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {

    Optional<Department> findDepartmentByName(String name);

}
