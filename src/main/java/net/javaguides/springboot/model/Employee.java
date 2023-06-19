package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="date_of_joining")
    private Date dateOfJoining;

    @OneToOne
    @JoinColumn(name = "adminId")
    private Admin admin; // One-to-Many relationship with Admin

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department; // Many-to-One relationship with Department

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();


    // private List<Leave> leaves; // One-to-Many relationship with Leave

    // private List<Salary> salaries; // One-to-Many relationship with Salary

    // private List<Attendance> attendanceRecords; // One-to-Many relationship with Attendance

    // private Role role; // Many-to-One relationship with Role

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {return phoneNumber;	}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;	}

    public String getAddress() {return address;	}
    public void setAddress(String address) {this.address = address;	}

    public Date getDateOfBirth() {return dateOfBirth;	}
    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;	}

    public Date getDateOfJoining() {return dateOfJoining;	}
    public void setDateOfJoining(Date dateOfJoining) {this.dateOfJoining = dateOfJoining;	}

}
