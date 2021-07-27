package com.rudikov.catalog.model.dto;

import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id", "name"})
@ToString(of = {"id", "name"})
public class DepartmentDTO {

    private Long id;
    private String name;
    private Set<EmployeeDTO> employees;

}
