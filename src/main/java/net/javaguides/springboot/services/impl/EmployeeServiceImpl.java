package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.payloads.EmployeeDTO;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        Employee employee = this.dtoToEmployee(employeeDto);
        Employee savedEmployee = this.employeeRepository.save(employee);
        return this.employeeToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDto, Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Emplyee", "Id", employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setPassword(employeeDto.getPassword());
        employee.setAddress(employeeDto.getAddress());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());

        Employee updatedEmployee = this.employeeRepository.save(employee);
        return this.employeeToDTO(updatedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Emplyee", "Id", employeeId));
        return this.employeeToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeDTO> EmployeesDto = employees.stream().map(employee -> this.employeeToDTO(employee)).collect(Collectors.toList());
        return EmployeesDto;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", employeeId));
        this.employeeRepository.delete(employee);
    }

    private Employee dtoToEmployee(EmployeeDTO employeeDto) {
        Employee employee = this.modelMapper.map(employeeDto, Employee.class);
        return employee;
    }

    private EmployeeDTO employeeToDTO(Employee employee) {
        EmployeeDTO employeeDto = this.modelMapper.map(employee, EmployeeDTO.class);
        return employeeDto;
    }
}
