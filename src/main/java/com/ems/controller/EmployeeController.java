package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public String addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return "Added Successfully.";
	}

	@GetMapping("allEmployee")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId) {
		return employeeService.getEmployeeById(employeeId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<Employee> getEmployeesByLocation(@RequestParam String location) {
		return employeeService.getEmployeeByLocation(location);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable("id") int employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "Deleted Successfully.";
	}

	@PutMapping
	public String updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
		return "Updated Successfully.";
	}
}
