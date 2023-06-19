package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class LeaveDTO {

    private int leaveId;

    @NotBlank(message = "leave reason is required")
    @Size(min = 10, max = 50, message = "leave reason must be min of 10 and max of 50 characters !!")
    private String leaveReason;

    @NotNull(message = "Leave start date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Leave end date is required in this format \"yyyy-MM-dd\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotBlank(message = "leave type is required")
    private String leaveType;

    @NotBlank(message = "leave status is required")
    private String leaveStatus;
}
