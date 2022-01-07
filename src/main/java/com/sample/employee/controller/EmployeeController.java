package com.sample.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sample.employee.model.Employee;
import com.sample.employee.service.EmployeeService;

/**
 * Controller that handles requests for the employees.
 */
@Controller
public class EmployeeController {

  /**
   * The employee service.
   */
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * List all employees.
	 * 
	 * @param model The model to add attribute.
	 * @return The employees page.
	 */
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Employee> listEmployees = (List<Employee>) employeeService.getAllEmployees();
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
	
	/**
	 * Add a new employee.
	 * 
	 * @param model The model to add attribute.
	 * @return The new employee page.
	 * 
	 */
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	/**
	 * Save employee.
	 * 
	 * @param employee The employee to save.
	 * 
	 * @return The page with all employees.
	 */
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	/**
	 * Update employee specified by id..
	 * 
	 * @param id The id of the employee to update.
	 * @param model The model to add attribute.
	 * 
	 * @return The update employee page.
	 */
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	/**
	 * Delete employee specified by id.
	 * 
	 * @param id The id of the employee to delete.
	 * 
	 * @return The page with all employees
	 */
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
}