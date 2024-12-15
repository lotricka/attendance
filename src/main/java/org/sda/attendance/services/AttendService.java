package org.sda.attendance.services;

import org.sda.attendance.models.Attendance;

import java.util.List;

public interface AttendService {

    public List<Attendance> getAttendance();
    public void updateAttendance(Attendance attendance);

    public void saveAttendance(Attendance attendance);

    Attendance getAttendById(Long id);

    List<Attendance> getIsPresent();

    List<Attendance> getAttendanceByDate(String date);

//    List<Attendance> getAttendanceByName(String name);


}
