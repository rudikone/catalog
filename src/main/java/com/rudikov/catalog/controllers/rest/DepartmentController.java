package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.DepartmentMapper;
import com.rudikov.catalog.exception.NotFoundDepartmentException;
import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "DepartmentApi")
public class DepartmentController {

    private DepartmentService departmentService;
    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/departments/{id}")
    @ApiOperation(value = "show department by id")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) throws NotFoundDepartmentException {
        Department department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentMapper.departmentToDepartmentDTO(department), HttpStatus.OK);

    }

    @GetMapping("/departments")
    @ApiOperation(value = "show all departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return departments != null && !departments.isEmpty()
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/departments")
    @ApiOperation(value = "add new department")
    public ResponseEntity<Department> addNewDepartment(@RequestBody String name) {
        Department department = new Department();
        department.setName(name);
        departmentService.save(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PutMapping("/admin/departments/{id}")
    @ApiOperation(value = "update department")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody String name) throws NotFoundDepartmentException {
        Department departmentFromDb = departmentService.update(id, name);
        return departmentFromDb != null
                ? new ResponseEntity<>(departmentFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/departments/{id}")
    @ApiOperation(value = "delete department")
    public void delete(@PathVariable Long id) {
        departmentService.remove(id);
    }
}
