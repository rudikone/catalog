package com.rudikov.catalog.converters;

import com.rudikov.catalog.exception.NoFoundEntityException;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.service.abstr.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest extends AbstractMapperTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        assertThat(employeeMapper).isNotNull();
        assertThat(departmentService).isNotNull();
    }

    @Test
    public void employeeToEmployeeDto_whenMaps_thenCorrect() {
        Department department = departmentService.findDepartmentByName("Отдел кадров");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setPersistDateTime(LocalDateTime.now());
        employee.setRank("Младший");
        employee.setPosition("Разработчик");
        employee.setFirstName("Коля");
        employee.setLastName("Колин");
        employee.setPhoneNumber("11-11");
        employee.setDepartment(department);

        EmployeeDTO employeeDTO = employeeMapper.employeeToEmployeeDTO(employee);

        assertEquals(employee.getId(), employeeDTO.getId());
        assertEquals(employee.getRank(), employeeDTO.getRank());
        assertEquals(employee.getPosition(), employeeDTO.getPosition());
        assertEquals(employee.getFirstName(), employeeDTO.getFirstName());
        assertEquals(employee.getLastName(), employeeDTO.getLastName());
        assertEquals(employee.getPhoneNumber(), employeeDTO.getPhoneNumber());
        assertEquals(employee.getDepartment().getName(), employeeDTO.getDepartment());
    }

    @Test
    public void employeeDtoToEmployee_whenMaps_thenCorrect() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setRank("Младший");
        employeeDTO.setPosition("Разработчик");
        employeeDTO.setFirstName("Коля");
        employeeDTO.setLastName("Колин");
        employeeDTO.setPhoneNumber("11-11");
        employeeDTO.setDepartment("Отдел кадров");

        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO, departmentService);

        assertEquals(employee.getId(), employeeDTO.getId());
        assertEquals(employee.getRank(), employeeDTO.getRank());
        assertEquals(employee.getPosition(), employeeDTO.getPosition());
        assertEquals(employee.getFirstName(), employeeDTO.getFirstName());
        assertEquals(employee.getLastName(), employeeDTO.getLastName());
        assertEquals(employee.getPhoneNumber(), employeeDTO.getPhoneNumber());
        assertEquals(employee.getDepartment().getName(), employeeDTO.getDepartment());
    }

    @Test
    public void employeeDtoToEmployee_whenMaps_thenDepartmentNotFound() {
        String name = "Отдел отделов";

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setRank("Младший");
        employeeDTO.setPosition("Разработчик");
        employeeDTO.setFirstName("Коля");
        employeeDTO.setLastName("Колин");
        employeeDTO.setPhoneNumber("11-11");
        employeeDTO.setDepartment(name);

        Throwable thrown = assertThrows(NoFoundEntityException.class, () -> {
            employeeMapper.employeeDTOtoEmployee(employeeDTO, departmentService);
        });
        assertNotNull(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Department с name=" + name + " не найден!"));
    }

}