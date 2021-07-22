package com.rudikov.catalog.model.dto;

import com.rudikov.catalog.model.entity.business.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(of = {"id", "name"})
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;
    private String name;
    private Set<EmployeeDTO> employeeDTOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeDTO> getEmployeeDTOs() {
        return employeeDTOs;
    }

    public void setEmployeeDTOs(Set<EmployeeDTO> employeeDTOs) {
        this.employeeDTOs = employeeDTOs;
    }
}
