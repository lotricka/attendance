package org.sda.attendance.controllers;

import org.sda.attendance.models.Attendance;
import org.sda.attendance.models.Employee;
import org.sda.attendance.services.AttendService;
import org.sda.attendance.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class MainController {

    private final AttendService attendService;

    private final EmployeeService employeeService;

    @Autowired
    public MainController(AttendService attendService, EmployeeService employeeService){
        this.attendService = attendService;
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(value = "search", required = false) String search,
                                             @RequestParam(value = "sortBy", required = false) String sortBy) {
        model.addAttribute("welcomeString", "Attendance table");
        model.addAttribute("attendances", attendService.getAttendance());
        if(Objects.nonNull(search)){
           model.addAttribute("attendances", attendService.getAttendanceByEmployee(search));
       } else if
        (Objects.nonNull(sortBy)){
            model.addAttribute("attendances", attendService.getAllSorted(sortBy));
        }
        return "index";
    }
    @GetMapping("/add-attendance")
    public String getAttendForm(Model model){
            model.addAttribute("employees", employeeService.getEmployees());
            return "add-attendance";
    }

    @PostMapping ("/add-attendance")
    public String createAttend(
            @RequestParam Long employeeId,
            @RequestParam String date,
            @RequestParam (required = false, defaultValue =  "false") boolean isPresent,
            @RequestParam (required = false, defaultValue = "0") int workedHours){

        Attendance attendance = new Attendance(isPresent, workedHours, date);
        Employee employee = employeeService.getById(employeeId);
        attendance.setEmployee(employee);
        attendService.saveAttendance(attendance);
              return "redirect:/";
    }


    @GetMapping("/edit-attendance/{id}")
    public String getAttendForm(@PathVariable("id") Long attendOrder,Model model) {
        model.addAttribute("attendance", attendService.getAttendById(attendOrder));
        return "edit-attendance";
    }
    @PostMapping("/edit-attendance/{id}")
    public String createAttend(@PathVariable("id") Long attendOrder,
                              // @RequestParam String name,
                               @RequestParam(required = false, defaultValue = "false") boolean isPresent,
                               @RequestParam float workedHours,
                               @RequestParam String date) {
        Attendance a = attendService.getAttendById(attendOrder);
//        a.setName(name);
        a.setPresent(isPresent);
        a.setWorkedHours(workedHours);
        a.setStringDate(date);

        attendService.updateAttendance(a);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable("id") Long id){
        attendService.deleteAttendance(id);
        return "redirect:/";
    }

    @GetMapping("/attendance-detail/{attendance}")
    public String showDetails(@PathVariable("attendance") Long attendOrder, Model model) {
        model.addAttribute("attendance", attendService.getAttendById(attendOrder));
        return "attendance-detail";
    }
    @GetMapping("/present")
    public String showPresent(Model model) {
        model.addAttribute("attendances", attendService.getIsPresent());
        return "present";
    }
    @GetMapping("/attend-by-date")
    public String searchAttendByDate(Model model, @RequestParam(value = "search", required = false) String search) {
        model.addAttribute("welcomeString", "Welcome to the Hell Attendance");
        if(Objects.nonNull(search)){
            model.addAttribute("attendances", attendService.getAttendanceByDate(search));
        } else {
            model.addAttribute("attendances", attendService.getAttendance());
        }
        return "attend-by-date";
    }
       }




