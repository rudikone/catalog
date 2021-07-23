package com.rudikov.catalog.converters;

import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.service.abstr.DepartmentService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(expression = "java( employee.getDepartment().getName() )", target = "department")
    public EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @Mapping(ignore = true, target = "department")
    public Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO, @Context DepartmentService service);

    @AfterMapping
    default void map(@MappingTarget Employee target, EmployeeDTO employeeDTO, @Context DepartmentService service) {
        target.setDepartment(service.findDepartmentByName(employeeDTO.getDepartment()));
    }
}

