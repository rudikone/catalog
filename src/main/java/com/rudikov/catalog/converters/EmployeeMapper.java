package com.rudikov.catalog.converters;

import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    public EmployeeDTO employeeToEmployeeDTO(Employee employee);

    public Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
