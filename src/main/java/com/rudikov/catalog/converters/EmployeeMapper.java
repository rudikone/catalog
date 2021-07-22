package com.rudikov.catalog.converters;

import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.repository.DepartmentRepo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(expression = "java( employee.getDepartment().getName() )", target = "department")
    public EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(ignore = true, target = "department")
    public Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO, @Context DepartmentRepo repo);

    @AfterMapping
    default void map(@MappingTarget Employee target, EmployeeDTO employeeDTO, @Context DepartmentRepo repo) {
        target.setDepartment(repo.findDepartmentByName(employeeDTO.getDepartment()).get());
    }
}

