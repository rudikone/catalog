package com.rudikov.catalog.converters;

import com.rudikov.catalog.exception.NoFoundEntityException;
import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.service.abstr.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class DepartmentMapperTest extends AbstractMapperTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        assertThat(employeeMapper).isNotNull();
        assertThat(employeeService).isNotNull();
        assertThat(departmentMapper).isNotNull();
    }

    @Test
    public void departmentToDepartmentDto_whenMaps_thenCorrect() {
        Set<Employee> employees = employeeService.findAllByDepartmentName("Отдел кадров");

        Department department = new Department();
        department.setId(1L);
        department.setPersistDateTime(LocalDateTime.now());
        department.setName("Отдел кадров");
        department.setEmployees(employees);

        DepartmentDTO departmentDTO = departmentMapper.departmentToDepartmentDTO(department);

        assertEquals(department.getName(), departmentDTO.getName());
        assertEquals(department.getId(), departmentDTO.getId());
        assertThat(departmentDTO.getEmployees()).isNotNull();
    }

    @Test
    public void departmentDtoToDepartment_whenMaps_thenCorrect() {
        Set<Employee> employees = employeeService.findAllByDepartmentName("Отдел кадров");
        Set<EmployeeDTO> employeeDTOSet = employeeListToEmployeeDTOList(employees);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Отдел кадров");
        departmentDTO.setEmployees(employeeDTOSet);

        Department department = departmentMapper.departmentDTOtoDepartment(departmentDTO, employeeService);

        assertEquals(departmentDTO.getName(), department.getName());
        assertEquals(departmentDTO.getId(), department.getId());
        assertThat(department.getEmployees()).isNotNull();
        assertThat(department.getPersistDateTime()).isNull();
    }


    @Test
    public void departmentDtoToDepartment_whenMaps_thenException() {
        String name = "Отдел отделов";

        Throwable thrown = assertThrows(NoFoundEntityException.class, () -> {
            employeeService.findAllByDepartmentName(name);
        });
        assertNotNull(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Department с name=" + name + " не найден!"));
    }

    public Set<EmployeeDTO> employeeListToEmployeeDTOList(Set<Employee> employees) {
        if (employees == null) {
            return null;
        }

        Set<EmployeeDTO> set = new HashSet<>(Math.max((int) (employees.size() / .75f) + 1, 16));
        for (Employee employee : employees) {
            set.add(employeeMapper.employeeToEmployeeDTO(employee));
        }

        return set;
    }
}
