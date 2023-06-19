package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.AdminDTO;

import java.util.List;

public interface AdminService {

    // create
    AdminDTO createAdmin(AdminDTO adminDTO);

    // update
    AdminDTO updateAdmin(AdminDTO adminDTO, Integer adminId);

    // delete
    void deleteAdmin(Integer adminId);

    // get
    AdminDTO getAdmin(Integer adminId);

    // get All
    List<AdminDTO> getAdmins();
}
