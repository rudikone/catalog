package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.exception.NoEntityException;
import com.rudikov.catalog.model.entity.Department;
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

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments/{id}")
    @ApiOperation(value = "show department by id")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) throws NoEntityException {
        Department department = departmentService.getDepartmentById(id);
        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
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
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody String name) throws NoEntityException {
        Department departmentFromDb = departmentService.update(id, name);
        return departmentFromDb != null
                ? new ResponseEntity<>(departmentFromDb, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/admin/departments/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.remove(id);
    }
}
