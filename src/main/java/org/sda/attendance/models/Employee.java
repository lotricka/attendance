package org.sda.attendance.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends BaseEntity {
    private String userName;
    private String firstName;
    private String lastName;
    private String position;

    public Employee() {
    }

    public Employee(String userName, String firstName, String lastName, String position) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }
    @OneToMany (mappedBy = "employee")
    private List<Attendance> attendances = new ArrayList<>();

    public String getUserName() {
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

}
