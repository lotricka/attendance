package org.sda.attendance;

import org.sda.attendance.models.Attendance;
import org.sda.attendance.models.Employee;
import org.sda.attendance.repositories.AttendRepository;
import org.sda.attendance.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class AttendanceApplication implements CommandLineRunner {

	private final AttendRepository attendRepository;
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AttendanceApplication(AttendRepository attendRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
		this.attendRepository = attendRepository;
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<Attendance> attendances = new ArrayList<>(List.of(
//				new Attendance("Petr Kubánek", true, 8.5F, "10-10-2024 05:00:11"),
//				new Attendance("Jani Vodhánělová", true, 8.0F, "10-11-2024 06:00:11"),
//				new Attendance("Michal Rostislav Rabinský", true, 8.5F, "10-12-2024 05:55:11"),
//				new Attendance("Milan Audy", false, 0F, "10-11-2024 05:00:11")
		List<Employee> employees = new ArrayList<>(List.of(
				new Employee("pepazdepa","Pepa", "Vomáčka", "developer", passwordEncoder.encode("123")),
				new Employee("coolboy","Petr", "Kubánek", "boss",passwordEncoder.encode ("123")),
				new Employee("justcounted","Iv", "Vacková", "student", passwordEncoder.encode("123"))));

//		for (Attendance attendance : attendances) {
//			attendRepository.save(attendance);
//		}
		for (Employee employee : employees){
			employee.setRoles(List.of("ROLE_ADMIN", "ROLE_EMPLOYEE"));
			employeeRepository.save(employee);
		}
		List<Attendance> attendances = new ArrayList<>(List.of(
				new Attendance(true, 8,"18-10-2024 05:00:11" ),
				new Attendance(true, 8,"19-10-2024 09:00:11" ),
				new Attendance(false, 0,"13-10-2024 05:00:11" ),
				new Attendance(true, 9,"10-10-2024 05:00:11" ),
				new Attendance(true, 7,"21-10-2024 05:00:11" ),
				new Attendance(true, 8,"10-10-2024 05:00:11" ),
				new Attendance(true, 8,"14-10-2024 07:00:11" ),
				new Attendance(false, 0,"15-10-2024 05:00:11" ),
				new Attendance(true, 8,"10-10-2024 05:00:11" ),
				new Attendance(false, 0,"13-10-2024 05:00:11" ),
				new Attendance(false, 0,"14-10-2024 05:00:11" )
				));

		attendances.get(0).setEmployee(employees.get(0));
		attendances.get(1).setEmployee(employees.get(0));
		attendances.get(2).setEmployee(employees.get(0));
		attendances.get(3).setEmployee(employees.get(1));
		attendances.get(4).setEmployee(employees.get(1));
		attendances.get(5).setEmployee(employees.get(1));
		attendances.get(6).setEmployee(employees.get(1));
		attendances.get(7).setEmployee(employees.get(2));
		attendances.get(8).setEmployee(employees.get(2));
		attendances.get(9).setEmployee(employees.get(2));
		attendances.get(10).setEmployee(employees.get(2));

		for (Attendance attendance : attendances) {
			attendRepository.save(attendance);
		}
	}
}