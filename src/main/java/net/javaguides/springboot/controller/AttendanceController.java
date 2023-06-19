package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.AttendanceDTO;
import net.javaguides.springboot.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // create attendance
    @PostMapping("/")
    public ResponseEntity<AttendanceDTO> createAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO createAttendance = this.attendanceService.createAttendance(attendanceDTO);
        return new ResponseEntity<>(createAttendance, HttpStatus.CREATED);
    }

    // update attendance
    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO, @PathVariable int attendanceId) {
        AttendanceDTO updatedSalary = this.attendanceService.updateAttendance(attendanceDTO, attendanceId);
        return new ResponseEntity<>(updatedSalary, HttpStatus.CREATED);
    }

    // delete attendance
    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<ApiResponse> deleteAttendance(@PathVariable int attendanceId) {
        this.attendanceService.deleteAttendance(attendanceId);
        return new ResponseEntity<>(new ApiResponse("Attendance is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get attendance
    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> getAttendance(@PathVariable int attendanceId) {
        AttendanceDTO attendanceDto = this.attendanceService.getAttendance(attendanceId);
        return new ResponseEntity<>(attendanceDto, HttpStatus.OK);
    }

    // get all attendances
    @GetMapping("/")
    public ResponseEntity<List<AttendanceDTO>> getAttendances() {
        List<AttendanceDTO> attendances = this.attendanceService.getAttendances();
        return ResponseEntity.ok(attendances);
    }
}
