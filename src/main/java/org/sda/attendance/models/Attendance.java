package org.sda.attendance.models;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity
@Table(name="attendance")

public class Attendance extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    Employee employee;

//    @Column(nullable = false)
//    private String name;

    @Column(name = "is_present")
    private boolean isPresent;

    @Column(name = "worked hours", columnDefinition = "int default 0")
    private float workedHours;

    private LocalDateTime date;

    public Attendance(boolean isPresent, float workedHours, String date) {
//        this.name = name;
        this.isPresent = isPresent;
        this.workedHours = workedHours;
        this.date = dateFromString(date);
    }
    private LocalDateTime dateFromString(String string) {
        if (string.contains("T")) {
            if (string.length() < 17) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(string + ":01", formatter);
                return dateTime;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
                return dateTime;
            }
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
            return dateTime;
        }
    }

    public Attendance() {
    }

//    public String getName() {
//        return name;
//    }

    public boolean isPresent() {
        return isPresent;
    }

    public float getWorkedHours() {
        return workedHours;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
//                ", name='" + name + '\'' +
                ", isPresent=" + isPresent +
                ", workedHours=" + workedHours +
                ", date=" + date +
                '}';
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public void setWorkedHours(float workedHours) {
        this.workedHours = workedHours;
    }

    public void setStringDate(String date) {
        this.date = dateFromString(date);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
