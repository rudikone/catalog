package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.EmployeeMapper;
import com.rudikov.catalog.model.dto.EmployeeDTO;
import com.rudikov.catalog.model.entity.Department;
import com.rudikov.catalog.model.entity.Employee;
import com.rudikov.catalog.model.entity.Role;
import com.rudikov.catalog.service.DepartmentService;
import com.rudikov.catalog.service.EmployeeService;
import com.rudikov.catalog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(value = "AdminApi")
public class AdminRestController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private RoleService roleService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public AdminRestController(EmployeeService employeeService, DepartmentService departmentService, RoleService roleService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.roleService = roleService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/employees/create")
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        employeeService.save(employee);
        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}")
    @ApiOperation(value = "show employee")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null
                ? new ResponseEntity<>(employeeMapper.employeeToEmployeeDTO(employee), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/employees/{id}")
    @ApiOperation(value = "update employee")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee newEmployee = employeeMapper.employeeDTOtoEmployee(employeeDTO);
        Employee employeeForUpdate = employeeService.getEmployeeById(id);
        employeeService.update(employeeForUpdate, newEmployee);
        //employeeService.save(newEmployee);
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

    @PostMapping("/roles/create")
    public ResponseEntity<List<Role>> addNewRole(@RequestBody Role role) {
        roleService.save(role);
        List<Role> roles = roleService.getAllRoles();
        return roles != null && !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles")
    @ApiOperation(value = "show all roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return roles != null && !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/departments/create")
    public ResponseEntity<List<Department>> addNewDepartment(@RequestBody Department department) {
        departmentService.save(department);
        List<Department> departments = departmentService.getAllDepartments();
        return departments != null && !departments.isEmpty()
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/departments")
    @ApiOperation(value = "show all departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return departments != null && !departments.isEmpty()
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
