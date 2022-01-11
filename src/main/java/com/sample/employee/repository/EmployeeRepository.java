package com.sample.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.employee.model.Employee;

/**
 * Repository used to manage employees
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  
  /**
   * Find an employee by specified email.
   * 
   * @param email The email of the employee
   * @return the employee with the given email, or <code>null</code> if not found.
   */
  public Employee findByEmail(String email);
  
  /**
   * Find all employees that are developers (junior or senior).
   * 
   * @return The list with all developers.
   */
  @Query(value = "SELECT * FROM employees WHERE role='Software Developer Junior' OR role='Software Developer Senior'", nativeQuery = true)
  public List<Employee> findAllDevelopers();

}
