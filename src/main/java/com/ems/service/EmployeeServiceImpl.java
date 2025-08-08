package com.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.addEmployee(employee);

	}

	@Override
	public List<Employee> getEmployeeByLocation(String location) {
		return employeeRepository.getEmployeesByLocation(location);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeeRepository.deleteEmployeeById(employeeId);

	}

	@Override
	public Optional<Employee> getEmployeeById(int employeeId) {
		return employeeRepository.getEmployeesById(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}

}
