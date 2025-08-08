package com.ems.service;

import java.util.List;
import java.util.Optional;

import com.ems.model.Employee;

public interface EmployeeService {

	void addEmployee(Employee employee);

	Optional<Employee> getEmployeeById(int employeeId);

	List<Employee> getEmployeeByLocation(String location);

	void deleteEmployee(int employeeId);

	void updateEmployee(Employee employee);

	List<Employee> getAllEmployees();

}
