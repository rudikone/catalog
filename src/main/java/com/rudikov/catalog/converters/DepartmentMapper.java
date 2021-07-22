package com.rudikov.catalog.converters;

import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.repository.EmployeeRepo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = EmployeeMapper.class)
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "department.employees", target = "employeeDTOs", qualifiedByName = "EmpToEmpDTO")
    public DepartmentDTO departmentToDepartmentDTO(Department department);

    @Mapping(source = "departmentDTO.employeeDTOs", target = "employees", ignore = true)
    public Department departmentDTOtoDepartment(DepartmentDTO departmentDTO, @Context EmployeeRepo repo);

    @Named("EmpToEmpDTO")
    public Set<EmployeeDTO> employeeListToEmployeeDTOList(Set<Employee> employees);


    @AfterMapping
    default void map(@MappingTarget Department target, DepartmentDTO departmentDTO, @Context EmployeeRepo repo) {
        target.setEmployees(repo.findAllByDepartmentName(departmentDTO.getName()).get());
    }
}
