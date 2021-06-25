package com.rudikov.catalog.converters;

import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

//    @Mapping(source = "employee.id", target = "id")
//    @Mapping(source = "employee.rank", target = "rank")
//    @Mapping(source = "employee.position", target = "position")
//    @Mapping(source = "employee.firstName", target = "firstName")
//    @Mapping(source = "employee.lastName", target = "lastName")
//    @Mapping(source = "employee.phoneNumber", target = "phoneNumber")
//    @Mapping(source = "employee.department", target = "department")
    public EmployeeDTO employeeToEmployeeDTO(Employee employee);

    public Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
