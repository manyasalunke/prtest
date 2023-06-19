package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.AdminDTO;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.DepartmentDTO;
import net.javaguides.springboot.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // create admin
    @PostMapping("/")
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        AdminDTO createAdmin = this.adminService.createAdmin(adminDTO);
        return new ResponseEntity<>(createAdmin, HttpStatus.CREATED);
    }

    // update admin
    @PutMapping("/{adminId}")
    public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO adminDTO, @PathVariable int adminId) {
        AdminDTO updatedAdmin = this.adminService.updateAdmin(adminDTO, adminId);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.CREATED);
    }

    // delete admin
    @DeleteMapping("/{adminId}")
    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable int adminId) {
        this.adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(new ApiResponse("Admin is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get admin
    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable int adminId) {
        AdminDTO adminDto = this.adminService.getAdmin(adminId);
        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

    // get all admins
    @GetMapping("/")
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        List<AdminDTO> admins = this.adminService.getAdmins();
        return ResponseEntity.ok(admins);
    }
}
