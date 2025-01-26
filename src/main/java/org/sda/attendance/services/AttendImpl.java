package org.sda.attendance.services;


import org.sda.attendance.models.Attendance;
import org.sda.attendance.repositories.AttendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AttendImpl implements AttendService {
    private final AttendRepository attendRepository;

    @Autowired
    public AttendImpl(AttendRepository attendRepository) {
        this.attendRepository = attendRepository;

    }

    @Override
    public List<Attendance> getAttendance() {
        return attendRepository.findAll();
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        attendRepository.save(attendance);
    }

    @Override
    public void saveAttendance(Attendance attendance) {
        attendRepository.save(attendance);
    }

    public void deleteAttendance(Long Id) {
        Optional<Attendance> attendance = attendRepository.findById(Id);
        if (attendance.isPresent()) {
            attendRepository.delete(attendance.get());
        }
    }

    @Override
    public Attendance getAttendById(Long id) {
        return attendRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Attendance with id" + id + "not found.")
        );

    }

    @Override
    public List<Attendance> getIsPresent() {
        return attendRepository.findAllisPresent();
    }

    @Override
    public List<Attendance> getAttendanceByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date + " 00:00:00", formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date + " 23:59:59", formatter);
        return attendRepository.findAttendanceByDate(dateTime, dateTime2);
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(String search) {
        return attendRepository.findAttendancesByEmployee_FirstNameContainsIgnoreCaseOrEmployee_LastNameContainsIgnoreCase(search, search);
    }

    @Override
    public List<Attendance> getAllSorted(String parameter) {
        return attendRepository.findAll(Sort.by(parameter));
    }
}

