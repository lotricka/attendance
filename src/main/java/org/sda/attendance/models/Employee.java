package org.sda.attendance.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Employee extends BaseEntity implements UserDetails {
    private String userName;
    private String firstName;
    private String lastName;
    private String position;
    private String password;

    public Employee() {
    }

    public Employee(String userName, String firstName, String lastName, String position, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.password = password;
    }

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_role", joinColumns = {@JoinColumn(name = "employee_id")})
    @Column(name = "roles")
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        for (String role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role));
        }
        return userRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}