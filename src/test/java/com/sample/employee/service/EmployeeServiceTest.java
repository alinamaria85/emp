package com.sample.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sample.employee.model.Employee;
import com.sample.employee.model.Role;
import com.sample.employee.repository.EmployeeRepository;

/**
 * Tests for the {@link EmployeeService}
 */
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	/**
	 * The employee repository.
	 */
	@Mock
	private EmployeeRepository employeeRepository;

	/**
	 * The employee service
	 */
	@InjectMocks
	private EmployeeService employeeService;


	/**
	 * Test the get all employees method.
	 */
	@Test
	public void testGetAllEmployees() {
		Employee emp1 = new Employee(1, "Anna", "Smith", "anna_smith@gmail.com", Role.QUALITY_ASSURANCE_SENIOR.getEmployeeRole());
		Employee emp2 = new Employee(2, "Ben", "White", "ben_white@gmail.com", Role.SYSTEM_ADMINISTRATOR.getEmployeeRole());

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);

		when(employeeRepository.findAll()).thenReturn(employees);

		List<Employee> allEmployees = (List<Employee>) employeeService.getAllEmployees();

		assertEquals(2, allEmployees.size());

		assertEmployee(allEmployees.get(0), emp1.getId(), emp1.getFirstName(), emp1.getLastName(), emp1.getEmail(), emp1.getRole());
		assertEmployee(allEmployees.get(1), emp2.getId(), emp2.getFirstName(), emp2.getLastName(), emp2.getEmail(), emp2.getRole());
	}
	

	/**
	 * Test the get by employee by id method.
	 */
	@Test
	public void testGetEmployeeById() {
		Employee emp1 = new Employee(1, "Anna", "Smith", "anna_smith@gmail.com", Role.QUALITY_ASSURANCE_SENIOR.getEmployeeRole());
		
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp1));
		
		Employee employeeById = employeeService.getEmployeeById(1L);
		assertNotNull(employeeById, "Couldn't found an employee with id 1");
		assertEmployee(employeeById, emp1.getId(), emp1.getFirstName(), emp1.getLastName(), emp1.getEmail(), emp1.getRole());
		
		when(employeeRepository.findById(2L)).thenThrow(RuntimeException.class);
	}

	/**
	 * Assert the employee.
	 * 
	 * @param employee The employee to be asserted.
	 * @param id The expected id.
	 * @param firstName The expected first name.
	 * @param lastName The expected last name.
	 * @param email The expected email.
	 * @param role The expected role.
	 */
	private void assertEmployee(Employee employee, long id, String firstName, String lastName, 
			String email, String role) {
		assertEquals(id, employee.getId(), "The id should be the same");
		assertEquals(firstName, employee.getFirstName(), "The first name should be the same");
		assertEquals(lastName, employee.getLastName(), "The last name should be the same");
		assertEquals(email, employee.getEmail(), "The email should be the same");
		assertEquals(role, employee.getRole(), "The role should be the same");
	}
}
