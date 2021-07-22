package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.EmployeeMapper;
import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "EmployeeApi")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

//    @PostMapping("/admin/employees")
//    @ApiOperation(value = "add new employee")
//    public ResponseEntity<Employee> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
//        employeeService.save(employee);
//        return new ResponseEntity<>(employee, HttpStatus.OK);
//    }

    @GetMapping("/employees/{id}")
    @ApiOperation(value = "show employee")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) throws NoEntityException {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(employee), HttpStatus.OK);
    }

//    @PatchMapping("/admin/employees/{id}")
//    @ApiOperation(value = "update employee")
//    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) throws NoEntityException {
//        Employee newEmployee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
//        Employee employeeForUpdate = employeeService.getEmployeeById(id);
//        employeeService.update(employeeForUpdate, newEmployee);
//        return newEmployee != null
//                ? new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(newEmployee), HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/employees")
    @ApiOperation(value = "show all employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> employeeMapper.employeeToEmployeeDTO(employee)).collect(Collectors.toList());
        return employeeDTOs != null && !employeeDTOs.isEmpty()
                ? new ResponseEntity<>(employeeDTOs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
