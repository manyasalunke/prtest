package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Attendance;
import net.javaguides.springboot.model.Salary;
import net.javaguides.springboot.payloads.AttendanceDTO;
import net.javaguides.springboot.payloads.SalaryDTO;
import net.javaguides.springboot.repository.AttendanceRepository;
import net.javaguides.springboot.repository.SalaryRepository;
import net.javaguides.springboot.services.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service()
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {
        Attendance attendance = this.modelMapper.map(attendanceDTO, Attendance.class);
        Attendance addedAttendance = this.attendanceRepository.save(attendance);
        return this.modelMapper.map(addedAttendance, AttendanceDTO.class);
    }

    @Override
    public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO, Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance ", "Attendance Id", attendanceId));

        attendance.setDateOfAttendance(attendanceDTO.getDateOfAttendance());
        attendance.setStatus(attendanceDTO.getStatus());
        attendance.setRemark(attendanceDTO.getRemark());
        attendance.setCreatedBy(attendanceDTO.getCreatedBy());

        Attendance UpdatedAttendance = this.attendanceRepository.save(attendance);
        return this.modelMapper.map(UpdatedAttendance, AttendanceDTO.class);
    }

    @Override
    public void deleteAttendance(Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance ", "Attendance id", attendanceId));
        this.attendanceRepository.delete(attendance);
    }

    @Override
    public AttendanceDTO getAttendance(Integer attendanceId) {
        Attendance attendance = this.attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "Attendance id", attendanceId));
        return this.modelMapper.map(attendance, AttendanceDTO.class);
    }

    @Override
    public List<AttendanceDTO> getAttendances() {
        List<Attendance> Attendances = this.attendanceRepository.findAll();
        List<AttendanceDTO> attendanceDto = Attendances.stream().map((attendance) -> this.modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
        return attendanceDto;
    }
}
