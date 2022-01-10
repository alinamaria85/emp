package com.sample.employee.service;

import com.sample.employee.model.Employee;

/**
 * Employee service.
 */
public interface EmployeeService {
	
	/**
	 * Get all employees.
	 * 
	 * @return The list of employees.
	 */
	Iterable<Employee> getAllEmployees();
	
	/**
	 * Save employee.
	 * 
	 * @param employee The employee to save.
	 */
	void saveEmployee(Employee employee);
	
	/**
	 * Get employee by id.
	 * 
	 * @param id The id of the employee.
	 * @return The employee with the given id.
	 */
	Employee getEmployeeById(long id);
	
	/**
	 * Delete the employee with the specified id.
	 * 
	 * @param id The id of the employee to delete.
	 */
	void deleteEmployeeById(long id);
}