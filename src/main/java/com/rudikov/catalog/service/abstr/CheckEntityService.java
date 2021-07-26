package com.rudikov.catalog.service.abstr;

public interface CheckEntityService {

    void checkExistDepartmentById(Long id);

    void checkExistDepartmentByName(String name);

    void checkExistEmployeeById(Long id);

}
