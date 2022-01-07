package com.sample.employee.service;

import org.springframework.data.domain.Page;

import com.sample.employee.model.Employee;

public interface EmployeeService {
	Iterable<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
}
