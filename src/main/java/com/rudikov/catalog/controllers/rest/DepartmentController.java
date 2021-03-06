package com.rudikov.catalog.controllers.rest;

import com.rudikov.catalog.converters.DepartmentMapper;
import com.rudikov.catalog.model.dto.DepartmentDTO;
import com.rudikov.catalog.model.entity.business.Department;
import com.rudikov.catalog.service.abstr.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentMapper.departmentToDepartmentDTO(department), HttpStatus.OK);

    }

    @GetMapping("/departments")
    @ApiOperation(value = "show all departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        List<DepartmentDTO> departmentDTOList = departments.stream().map(department -> departmentMapper.departmentToDepartmentDTO(department)).collect(Collectors.toList());
        return !departmentDTOList.isEmpty()
                ? new ResponseEntity<>(departmentDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/not_dto_departments")
    @ApiOperation(value = "show all departments")
    public ResponseEntity<List<Department>> getAllNotDtoDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return !departments.isEmpty()
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/departments")
    @ApiOperation(value = "add new department")
    public ResponseEntity<DepartmentDTO> addNewDepartment(@RequestBody String name) {
        Department department = new Department();
        department.setName(name);
        Department savedDepartment = departmentService.save(department);
        return new ResponseEntity<>(departmentMapper.departmentToDepartmentDTO(savedDepartment), HttpStatus.OK);
    }

    @PutMapping("/admin/departments/{id}")
    @ApiOperation(value = "update department")
    public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody String name) {
        Department departmentFromDb = departmentService.getDepartmentById(id);
        departmentFromDb.setName(name);
        Department savedDepartment = departmentService.save(departmentFromDb);
        return new ResponseEntity<>(departmentMapper.departmentToDepartmentDTO(savedDepartment), HttpStatus.OK);
    }

    @DeleteMapping("/admin/departments/{id}")
    @ApiOperation(value = "delete department")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.remove(id);
    }
}
