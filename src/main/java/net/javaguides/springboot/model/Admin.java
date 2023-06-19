package net.javaguides.springboot.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(name="userName",length = 15,nullable = false)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id")
    private String emailId;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> managedEmployees; // One-to-Many relationship with Employee

    @OneToMany(mappedBy = "admin")
    private List<Leave> managedLeaveRecords; // One-to-Many relationship with Leave

    @OneToMany(mappedBy = "admin")
    private List<Salary> managedSalaryRecords; // One-to-Many relationship with Salary

    @OneToMany(mappedBy = "admin")
    private List<Attendance> managedAttendanceRecords; // One-to-Many relationship with Attendance

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_role",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

}
