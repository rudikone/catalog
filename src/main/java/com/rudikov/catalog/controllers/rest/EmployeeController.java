package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.EmployeeMapper;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.business.Employee;
import com.rudikov.catalog.service.abstr.CheckEntityService;
import com.rudikov.catalog.service.abstr.DepartmentService;
import com.rudikov.catalog.service.abstr.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    private DepartmentService departmentService;
    private CheckEntityService checkEntityService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              EmployeeMapper employeeMapper,
                              DepartmentService departmentService,
                              CheckEntityService checkEntityService) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.departmentService = departmentService;
        this.checkEntityService = checkEntityService;
    }

    @PostMapping("/admin/employees")
    @ApiOperation(value = "add new employee")
    public ResponseEntity<EmployeeDTO> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        checkEntityService.checkExistDepartmentByName(employeeDTO.getDepartment());
        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO, departmentService);
        Employee savedEmployee = employeeService.save(employee);
        checkEntityService.checkNullableOrSavedEntity(savedEmployee);
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(savedEmployee), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    @ApiOperation(value = "show employee")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        checkEntityService.checkExistEmployeeById(id);
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(employee), HttpStatus.OK);
    }

    @PatchMapping("/admin/employees/{id}")
    @ApiOperation(value = "update employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,
                                                      @RequestBody EmployeeDTO employeeDTO) {
        checkEntityService.checkExistEmployeeById(id);
        Employee employeeFromDb = employeeService.getEmployeeById(id);
        checkEntityService.checkExistDepartmentByName(employeeDTO.getDepartment());
        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO, departmentService);
        BeanUtils.copyProperties(employee, employeeFromDb, "id");
        Employee savedEmployee = employeeService.save(employeeFromDb);
        checkEntityService.checkNullableOrSavedEntity(savedEmployee);
        return new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(savedEmployee), HttpStatus.OK);
    }

    @GetMapping("/employees")
    @ApiOperation(value = "show all employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = employees.stream().map(employee -> employeeMapper.employeeToEmployeeDTO(employee)).collect(Collectors.toList());
        return !employeeDTOs.isEmpty()
                ? new ResponseEntity<>(employeeDTOs, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/employees/{id}")
    @ApiOperation(value = "delete employee")
    public void deleteEmployee(@PathVariable Long id) {
        checkEntityService.checkExistEmployeeById(id);
        employeeService.remove(id);
    }
}
