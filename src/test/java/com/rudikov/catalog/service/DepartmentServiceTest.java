package com.rudikov.catalog.service;

import com.rudikov.catalog.exception.NotFoundDepartmentException;
import com.rudikov.catalog.model.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenValidDepartmentNameThenOk() throws NotFoundDepartmentException {
        Long id = 1L;
        Department found = departmentService.getDepartmentById(id);

        assertEquals(id, found.getId());
    }
}