package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.model.entity.business.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest extends AbstractControllerTest {

    @Test
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
        assertThat(employeeRepo).isNotNull();
    }

    @Test
    void isAuthenticated() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void addNewEmployeeStatusOk() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Иван");
        employeeDTO.setLastName("Иванов");
        employeeDTO.setRank("Главный");
        employeeDTO.setPosition("Менеджер");
        employeeDTO.setDepartment("Хозяйственный отдел");
        employeeDTO.setPhoneNumber("11-11");

        String jsonRequest = objectMapper.writeValueAsString(employeeDTO);

        this.mockMvc.perform(post("/admin/employees")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("firstName").value(employeeDTO.getFirstName()))
                .andExpect(jsonPath("lastName").value(employeeDTO.getLastName()))
                .andExpect(jsonPath("rank").value(employeeDTO.getRank()))
                .andExpect(jsonPath("position").value(employeeDTO.getPosition()))
                .andExpect(jsonPath("phoneNumber").value(employeeDTO.getPhoneNumber()))
                .andExpect(jsonPath("department").value(employeeDTO.getDepartment()))
                .andExpect(status().isOk());

        assertThat(employeeRepo.findByFirstNameAndLastNameAndRankAndPosition(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getRank(),
                employeeDTO.getPosition()).get().getFirstName())
                .isEqualTo(employeeDTO.getFirstName());
    }

    @Test
    void addNewEmployeeStatusNotFound() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Иван");
        employeeDTO.setLastName("Иванов");
        employeeDTO.setRank("младший");
        employeeDTO.setPosition("Менеджер");
        employeeDTO.setDepartment("Отдел СБ");
        employeeDTO.setPhoneNumber("11-11");

        String jsonRequest = objectMapper.writeValueAsString(employeeDTO);

        this.mockMvc.perform(post("/admin/employees")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с name=" + employeeDTO.getDepartment() + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(employeeRepo.findByFirstNameAndLastNameAndRankAndPosition(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getRank(),
                employeeDTO.getPosition()).orElse(new Employee()).getFirstName())
                .isNull();
    }

    @Test
    void getEmployeeByIdStatusOk() throws Exception {
        int id = 1;

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Коля");
        employeeDTO.setLastName("Колин");
        employeeDTO.setRank("Младший");
        employeeDTO.setPosition("Разработчик");
        employeeDTO.setDepartment("Отдел кадров");
        employeeDTO.setPhoneNumber("11-11");

        this.mockMvc.perform(get("/employees/" + id))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("firstName").value(employeeDTO.getFirstName()))
                .andExpect(jsonPath("lastName").value(employeeDTO.getLastName()))
                .andExpect(jsonPath("rank").value(employeeDTO.getRank()))
                .andExpect(jsonPath("position").value(employeeDTO.getPosition()))
                .andExpect(jsonPath("phoneNumber").value(employeeDTO.getPhoneNumber()))
                .andExpect(jsonPath("department").value(employeeDTO.getDepartment()))
                .andExpect(status().isOk());

        assertThat(employeeRepo.findById((long) id).orElse(new Employee()).getFirstName()).isEqualTo(employeeDTO.getFirstName());
    }

    @Test
    void getEmployeeByIdStatusNotFound() throws Exception {
        int id = 100;
        this.mockMvc.perform(get("/employees/" + id))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Employee с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(employeeRepo.findById((long) id).orElse(new Employee()).getFirstName()).isNull();
    }

    @Test
    void updateEmployeeStatusOk() throws Exception {
        int id = 1;

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Иван");
        employeeDTO.setLastName("Иванов");
        employeeDTO.setRank("Главный");
        employeeDTO.setPosition("Конструктор");
        employeeDTO.setDepartment("Хозяйственный отдел");
        employeeDTO.setPhoneNumber("11-11");

        String jsonRequest = objectMapper.writeValueAsString(employeeDTO);

        this.mockMvc.perform(patch("/admin/employees/" + id)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("firstName").value(employeeDTO.getFirstName()))
                .andExpect(jsonPath("lastName").value(employeeDTO.getLastName()))
                .andExpect(jsonPath("rank").value(employeeDTO.getRank()))
                .andExpect(jsonPath("position").value(employeeDTO.getPosition()))
                .andExpect(jsonPath("phoneNumber").value(employeeDTO.getPhoneNumber()))
                .andExpect(jsonPath("department").value(employeeDTO.getDepartment()))
                .andExpect(status().isOk());

        assertThat(employeeRepo.findById((long) id).get().getFirstName()).isEqualTo(employeeDTO.getFirstName());
    }

    @Test
    void updateEmployeeStatusEmployeeNotFound() throws Exception {
        int id = 100;

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Иван");
        employeeDTO.setLastName("Иванов");
        employeeDTO.setRank("Главный");
        employeeDTO.setPosition("Конструктор");
        employeeDTO.setDepartment("Хозяйственный отдел");
        employeeDTO.setPhoneNumber("11-11");

        String jsonRequest = objectMapper.writeValueAsString(employeeDTO);

        this.mockMvc.perform(patch("/admin/employees/" + id)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Employee с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(employeeRepo.findById((long) id).orElse(new Employee()).getFirstName()).isNull();
    }

    @Test
    void updateEmployeeStatusDepartmentNotFound() throws Exception {
        int id = 1;

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("Иван");
        employeeDTO.setLastName("Иванов");
        employeeDTO.setRank("Главный");
        employeeDTO.setPosition("Конструктор");
        employeeDTO.setDepartment("Отдел СБ");
        employeeDTO.setPhoneNumber("11-11");

        String jsonRequest = objectMapper.writeValueAsString(employeeDTO);

        this.mockMvc.perform(patch("/admin/employees/" + id)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с name=" + employeeDTO.getDepartment() + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(departmentRepo.findDepartmentByName(employeeDTO.getDepartment()).orElse(new Department()).getId()).isNull();
    }

    @Test
    void getAllEmployeesStatusOk() throws Exception {
        this.mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string(containsString("id")))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployeeStatusOk() throws Exception {
        int id = 2;
        mockMvc.perform(delete("/admin/employees/" + id))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(status().isOk());

        assertThat(employeeRepo.findById((long) id).orElse(new Employee()).getId()).isNull();
    }

    @Test
    void deleteEmployeeStatusNotFound() throws Exception {
        int id = 100;
        mockMvc.perform(delete("/admin/employees/" + id))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Employee с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(employeeRepo.findById((long) id).orElse(new Employee()).getId()).isNull();
    }
}