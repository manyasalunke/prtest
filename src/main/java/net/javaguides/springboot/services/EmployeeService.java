package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employee);
    EmployeeDTO updateEmployee(EmployeeDTO employee, Integer employeeId);
    EmployeeDTO getEmployeeById(Integer employeeId);
    List<EmployeeDTO> getAllEmployees();
    void deleteEmployee(Integer employeeId);
}
