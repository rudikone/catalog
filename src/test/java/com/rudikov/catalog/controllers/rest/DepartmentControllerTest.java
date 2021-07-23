package com.rudikov.catalog.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-department-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-employee-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-department-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/sql/create-employee-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DepartmentControllerTest {

    @Autowired
    private DepartmentController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void isAuthenticated() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void getDepartmentByIdStatusOk() throws Exception {
//        EmployeeDTO employeeDTO = new EmployeeDTO();
//        employeeDTO.setId(1L);
//        employeeDTO.setFirstName("Иван");
//        employeeDTO.setLastName("Иванов");
//        employeeDTO.setRank("Главный");
//        employeeDTO.setPosition("Менеджер");
//        employeeDTO.setDepartment("Отдел кадров");
//        employeeDTO.setPhoneNumber("11-11");
//
//        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
//        employeeDTOSet.add(employeeDTO);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Отдел кадров");


        this.mockMvc.perform(get("/departments/1"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("id").value(departmentDTO.getId()))
                .andExpect(jsonPath("name").value(departmentDTO.getName()))
                .andExpect(jsonPath("employees").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentByIdStatusNotFound() throws Exception {
        int id = 100;
        this.mockMvc.perform(get("/departments/" + id))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllDepartments() throws Exception {

        this.mockMvc.perform(get("/departments"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string(containsString("id")))
                .andExpect(status().isOk());
    }

    @Test
    void addNewDepartment() throws Exception {
        String name = "Отдел безопасности";

        this.mockMvc.perform(post("/admin/departments")
                .content(name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("employees").isEmpty())
                .andExpect(status().isOk());

    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }
}