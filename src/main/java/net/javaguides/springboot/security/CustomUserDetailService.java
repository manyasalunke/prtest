package net.javaguides.springboot.security;

import net.javaguides.springboot.model.Admin;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.AdminRepository;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailService(AdminRepository adminRepository, EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to retrieve an Admin entity with the provided username
        Optional<Admin> adminOptional = adminRepository.findByUserName(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return new UserAdapter(admin);
        }

        // Try to retrieve an Employee entity with the provided username
        Optional<Employee> employeeOptional = employeeRepository.findByUserName(username);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return new UserAdapter(employee);
        }

        // If no user is found with the provided username, throw an exception
        throw new UsernameNotFoundException("User not found");
    }
}
