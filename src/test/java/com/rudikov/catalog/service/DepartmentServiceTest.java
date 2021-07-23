package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.service.abstr.DepartmentService;
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
    public void whenValidDepartmentNameThenOk() {
        Long id = 1L;
        Department found = departmentService.getDepartmentById(id);

        assertEquals(id, found.getId());
    }
}