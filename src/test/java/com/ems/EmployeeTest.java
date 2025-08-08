package com.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;

@SpringBootTest
public class EmployeeTest {

	@Autowired
	EmployeeService employeeService;

	@Test
	public void addEmployeeTest() {
		int empId = 1;
		Employee employee = new Employee(empId, "Aarush", "K", "Bangalore");
		employeeService.addEmployee(employee);

		Optional<Employee> empData = employeeService.getEmployeeById(empId);
		assertTrue(empData.isPresent());
		Employee employeeData = empData.get();
		assertEquals("Aarush", employeeData.getFirstName());

		assertThrows(DuplicateKeyException.class, () -> {
			employeeService.addEmployee(employee);
		});

	}

	@Test
	public void inquiryEmployeeByLocationTest() {

		Employee employee = new Employee(2, "Sita", "K", "Bangalore");
		employeeService.addEmployee(employee);
		Employee employee2 = new Employee(3, "Ram", "Singh", "bangalore");
		employeeService.addEmployee(employee2);
		Employee employee3 = new Employee(4, "M", "Srinivas", "Delhi");
		employeeService.addEmployee(employee3);

		List<Employee> empList = employeeService.getEmployeeByLocation("Bangalore");
		assertNotNull(empList);
		assertEquals(2, empList.size());

		empList = employeeService.getEmployeeByLocation("Delhi");
		assertNotNull(empList);
		assertEquals(1, empList.size());

		List<Employee> allEmpList = employeeService.getAllEmployees();
		assertNotNull(allEmpList);
		assertEquals(3, allEmpList.size());
	}

	@Test
	public void deleteEmployee() {
		int empId = 5;
		Employee employee = new Employee(empId, "Pratyush", "Kumar", "Hyderabad");
		employeeService.addEmployee(employee);
		Employee employee1 = new Employee(6, "M", "Rajiv", "Delhi");
		employeeService.addEmployee(employee1);

		Optional<Employee> empData = employeeService.getEmployeeById(5);
		assertTrue(empData.isPresent());

		employeeService.deleteEmployee(empData.get().getEmployeeId());

		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeService.getEmployeeById(empId);
		});

		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeService.deleteEmployee(empData.get().getEmployeeId());
		});

	}

}
