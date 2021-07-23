package com.rudikov.catalog.service;

import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.service.abstr.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void whenValidDepartmentNameThenOk() {
        Long id = 1L;
        Department found = departmentService.getDepartmentById(id);

        assertEquals(id, found.getId());
    }
}