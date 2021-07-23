package com.rudikov.catalog.service.abstr;

import com.rudikov.catalog.exception.NoFoundEntityException;

public interface CheckEntityService {

    public void checkExistDepartmentById(Long id);

    public void checkExistDepartmentByName(String name);

    public void checkExistEmployeeById(Long id);

    public void checkNullableOrSavedEntity(Object o);
}
