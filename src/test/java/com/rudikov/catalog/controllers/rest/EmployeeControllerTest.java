package com.rudikov.catalog.controllers.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
class EmployeeControllerTest {

//    @Autowired
//    private EmployeeController employeeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewEmployee() {
    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void addNewRole() {
    }

    @Test
    void getAllRoles() {
    }

    @Test
    void addNewDepartment() {
    }

    @Test
    void getAllDepartments() {
    }
}