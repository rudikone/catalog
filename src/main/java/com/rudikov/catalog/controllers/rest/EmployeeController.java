package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.EmployeeMapper;
import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.Employee;
import com.rudikov.catalog.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/admin/employees/create")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        employeeService.save(employee);
        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}")
    @ApiOperation(value = "show employee")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) throws NoEntityException {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(employee), HttpStatus.OK);
    }

    @PatchMapping("/admin/employees/{id}")
    @ApiOperation(value = "update employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) throws NoEntityException {
        Employee newEmployee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        Employee employeeForUpdate = employeeService.getEmployeeById(id);
        employeeService.update(employeeForUpdate, newEmployee);
        return newEmployee != null
                ? new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(newEmployee), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees")
    @ApiOperation(value = "show all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
