package com.sample.employee.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sample.employee.model.Employee;

/**
 * Tests for {@link EmployeeRepository}
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryTest {
  
  /**
   * The employee repository.
   */
  @Autowired
  private EmployeeRepository employeeRepository;
  
  /**
   * The data source
   */
  @Autowired
  protected DataSource datasource;
  

  /**
   * Assert the find all employees method
   */
  @Test
  public void testFindAllEmployees() {
    // Assert the employees number
    List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();
    assertEquals(6, allEmployees.size(), "Should be 6 employees");
    // Assert the employees
    assertEmployee(allEmployees.get(0), 1, "John", "Tailor", "john_tailor@gmail.com");
    assertEmployee(allEmployees.get(1), 2, "Olivia", "Wilson", "olivia_wilson@gmail.com");
    assertEmployee(allEmployees.get(2), 3, "William", "Brown", "wiliam_brown@gmail.com");
    assertEmployee(allEmployees.get(3), 4, "Emma", "White", "emma_white@gmail.com");
    assertEmployee(allEmployees.get(4), 5, "Oliver", "Smith", "oliver_smith@gmail.com");
    assertEmployee(allEmployees.get(5), 6, "Victoria", "Williams", "victoria_williams@gmail.com");
  }
  
  /**
   * Test the save method.
   */
  @Test
  void testSaveEmployee() {
    Employee emp = new Employee();
    emp.setFirstName("John");
    emp.setLastName("Doe");
    emp.setEmail("john_doe@gmail.com");
    
    // Assert the saved employee
    Employee savedEmp = employeeRepository.save(emp);
    assertEmployee(savedEmp, 7, "John", "Doe", "john_doe@gmail.com");
    
    // Assert the number of employees
    List<Employee>  allEmployees = (List<Employee>) employeeRepository.findAll();
    assertEquals(7, allEmployees.size(), "Should be 7 employees");
  }
  
  /**
   * Test the get employee by id method.
   */
  @Test
  public void testGetEmployeeById() {
    Optional<Employee> foundEmp = employeeRepository.findById(3L);
    
    // Assert that employee exist, and assert his info
    assertTrue(foundEmp.isPresent(), "The employee with id 3 wasn't found");
    assertEmployee(foundEmp.get(), 3, "William", "Brown", "wiliam_brown@gmail.com");
  }
  
  @Test
  void testDeleteEmployeeById() {
    employeeRepository.deleteById(3L);
    
    // Assert the employees number
    List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();
    assertEquals(5, allEmployees.size(), "Should be 6 employees");
    // Assert the employees
    assertEmployee(allEmployees.get(0), 1, "John", "Tailor", "john_tailor@gmail.com");
    assertEmployee(allEmployees.get(1), 2, "Olivia", "Wilson", "olivia_wilson@gmail.com");
    assertEmployee(allEmployees.get(2), 4, "Emma", "White", "emma_white@gmail.com");
    assertEmployee(allEmployees.get(3), 5, "Oliver", "Smith", "oliver_smith@gmail.com");
    assertEmployee(allEmployees.get(4), 6, "Victoria", "Williams", "victoria_williams@gmail.com");
  }
  
  
  /**
   * Assert the employee.
   * 
   * @param employee The employee to be asserted.
   * @param id The expected id.
   * @param firstName The expected first name.
   * @param lastName The expected last name.
   * @param email The expected email.
   */
  private void assertEmployee(Employee employee, long id, String firstName, String lastName, String email) {
    assertEquals(id, employee.getId(), "The id should be the same");
    assertEquals(firstName, employee.getFirstName(), "The first name should be the same");
    assertEquals(lastName, employee.getLastName(), "The last name should be the same");
    assertEquals(email, employee.getEmail(), "The email should be the same");
  }
  
  /**
   * Executes SQL scripts before the class execution.
   * 
   * @throws SQLException When one of the scripts cannot be executed.
   * @throws IOException  If the properties file containing the DB credentials cannot be read.
   */
  @BeforeEach
  public void beforeEach() throws SQLException, IOException {
    run("populateDB.sql", datasource);
  }
  
  /**
   * Executes SQL scripts after the class execution.
   * 
   * @throws SQLException When one of the scripts cannot be executed.
   * @throws IOException  If the properties file containing the DB credentials cannot be read.
   */
  @AfterEach
  public void afterEach() throws SQLException, IOException {
    run("clearRecords.sql", datasource);
  }
  
  /**
   * Runs the given sql file script.
   * 
   * @param sqlFileScript The sql file script to run.
   * 
   * @throws IOException If something goes wrong.
   */
  private static void run(String sqlFileScript, DataSource dataSource) {
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.setScripts(new ClassPathResource(sqlFileScript));
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
  }
}
