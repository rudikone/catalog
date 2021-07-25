package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.entity.business.Department;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class DepartmentControllerTest extends AbstractControllerTest {

    @Test
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
        assertThat(departmentRepo).isNotNull();
    }

    @Test
    void isAuthenticated() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    void getDepartmentByIdStatusOk() throws Exception {
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

        assertThat(departmentRepo.findById(departmentDTO.getId()).get().getName()).isEqualTo(departmentDTO.getName());
    }

    @Test
    void getDepartmentByIdStatusNotFound() throws Exception {
        int id = 100;
        this.mockMvc.perform(get("/departments/" + id))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(departmentRepo.findById((long) id).orElse(new Department()).getName()).isNull();
    }

    @Test
    void getAllDepartmentsStatusOk() throws Exception {

        this.mockMvc.perform(get("/departments"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string(containsString("id")))
                .andExpect(status().isOk());
    }

    @Test
    void addNewDepartmentStatusOk() throws Exception {
        String name = "Отдел безопасности";

        this.mockMvc.perform(post("/admin/departments")
                .content(name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("employees").isEmpty())
                .andExpect(status().isOk());

        assertThat(departmentRepo.findDepartmentByName(name)).isNotNull();
    }

    @Test
    void updateStatusOk() throws Exception {
        String name = "Отдел отделов";
        int id = 1;

        this.mockMvc.perform(put("/admin/departments/" + id)
                .content(name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("employees").isNotEmpty())
                .andExpect(status().isOk());

        assertThat(departmentRepo.findById((long) id).get().getName()).isEqualTo(name);
    }

    @Test
    void updateStatusNotFound() throws Exception {
        String name = "Отдел отделов";
        int id = 100;

        this.mockMvc.perform(put("/admin/departments/" + id)
                .content(name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(departmentRepo.findById((long) id).orElse(new Department()).getId()).isNull();
    }

    @Test
    void deleteDepartmentStatusOk() throws Exception {
        int id = 2;
        mockMvc.perform(delete("/admin/departments/" + id))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(status().isOk());

        assertThat(departmentRepo.findById((long) id).orElse(new Department()).getId()).isNull();
    }

    @Test
    void deleteDepartmentStatusNotFound() throws Exception {
        int id = 100;
        mockMvc.perform(delete("/admin/departments/" + id))
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(content().string("Department с id=" + id + " не найден!"))
                .andExpect(status().isNotFound());

        assertThat(departmentRepo.findById((long) id).orElse(new Department()).getId()).isNull();
    }
}