package com.rudikov.catalog.model.dto;

import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, of = {"name"})
@ToString(of = {"name"})
public class DepartmentDTO extends BaseDTO{

    private String name;
    private Set<EmployeeDTO> employees;

}
