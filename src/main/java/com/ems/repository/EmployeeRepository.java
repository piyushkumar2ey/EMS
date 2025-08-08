package com.ems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ems.exception.EmployeeNotFoundException;
import com.ems.mapper.EmployeeRowMapper;
import com.ems.model.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int addEmployee(Employee employee) {
		String sql = "INSERT INTO employee(employee_id, first_name, last_name, location) VALUES (?, ?, ?, ?)";
		
		return jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
				employee.getLocation());
	}

	public List<Employee> getEmployeesByLocation(String location) {
		String sql = "SELECT * FROM employee WHERE LOWER(location) = LOWER(?)";
		
		return jdbcTemplate.query(sql, new EmployeeRowMapper(), location);
	}

	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";
		
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	public Optional<Employee> getEmployeesById(int employeeId) {
		String sql = "SELECT * FROM employee WHERE employee_id = ?";
		
		try {
			Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeId);
			return Optional.ofNullable(employee);

		} catch (EmptyResultDataAccessException e) {
			throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
		}
	}

	public int deleteEmployeeById(int employeeId) {
		String sql = "DELETE FROM employee WHERE employee_id = ?";
		
		int rows = jdbcTemplate.update(sql, employeeId);
		
		if (rows == 0) {
			throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
		}
		return rows;
	}
}
