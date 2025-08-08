CREATE TABLE IF NOT EXISTS employee (employee_id INT PRIMARY KEY, first_name VARCHAR(50),
				        last_name VARCHAR(50), location VARCHAR(50)
				        );
CREATE INDEX IF NOT EXISTS idx_employee_location ON employee(location);
				        