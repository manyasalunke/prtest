package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.EmployeeDTO;
import net.javaguides.springboot.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // POST-create employee
    @PostMapping("/")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployeeDto = this.employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    // PUT- update employee
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable int id) {
        EmployeeDTO updatedEmployee = this.employeeService.updateEmployee(employeeDTO, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    // DELETE -delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        this.employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new ApiResponse("Employee deleted Successfully", true), HttpStatus.OK);
    }

    // GET - employee get all
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    // GET - employee get
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getSingleEmployee(@PathVariable int id) {
        return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
    }
}
