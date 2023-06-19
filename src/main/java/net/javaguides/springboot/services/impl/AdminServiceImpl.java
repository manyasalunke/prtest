package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Admin;
import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.payloads.AdminDTO;
import net.javaguides.springboot.payloads.DepartmentDTO;
import net.javaguides.springboot.repository.AdminRepository;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = this.modelMapper.map(adminDTO, Admin.class);
        Admin addedAdmin = this.adminRepository.save(admin);
        return this.modelMapper.map(addedAdmin, AdminDTO.class);
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO, Integer adminId) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin ", "Admin Id", adminId));

        admin.setUserName((adminDTO.getUserName()));
        admin.setPassword(adminDTO.getPassword());
        admin.setEmailId(adminDTO.getEmailId());

        Admin updatedAdmin = this.adminRepository.save(admin);
        return this.modelMapper.map(updatedAdmin, AdminDTO.class);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin ", "Admin id", adminId));
        this.adminRepository.delete(admin);
    }

    @Override
    public AdminDTO getAdmin(Integer adminId) {
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "Admin id", adminId));
        return this.modelMapper.map(admin, AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAdmins() {
        List<Admin> admins = this.adminRepository.findAll();
        List<AdminDTO> adminDto = admins.stream().map((admin) -> this.modelMapper.map(admin, AdminDTO.class))
                .collect(Collectors.toList());
        return adminDto;
    }
}
