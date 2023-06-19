package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AttendanceDTO {
    private Integer attendanceId;

    @NotNull(message = "Attendance date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Attendance date must be in the present or future day in this format \"yyyy-MM-dd\"")
    private Date dateOfAttendance;

    // Status of the attendance, such as "Present," "Absent," "Late," or "Early Departure."
    @NotBlank(message = "status is required")
    @Pattern(regexp = "^(Present|Absent|Late|Early Departure)$", message = "Invalid status. Allowed values: Present, Absent, Late, Early Departure")
    private String status;

    @NotBlank(message = "remark is required")
    private String remark;

    //User or admin who created the attendance record.
    @NotBlank(message = "created By is required")
    @Pattern(regexp = "^(Admin|Employee)$", message = "Invalid status. Allowed values: Admin, Employee")
    private String createdBy;
}
