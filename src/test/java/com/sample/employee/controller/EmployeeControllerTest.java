package com.sample.employee.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.employee.model.Employee;
import com.sample.employee.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
  
  /**
   * Object for testing the MVC controller.
   */
  @Autowired
  private MockMvc mvc;
  
  @MockBean
  private EmployeeService employeeService;

  @Test
  public void testViewHomePage() throws Exception {
    Employee emp1 = new Employee();
    emp1.setFirstName("First");
    emp1.setLastName("Employee");
    emp1.setEmail("emp1@gmail.com");
    emp1.setId(1);
    
    Employee emp2 = new Employee();
    emp2.setFirstName("Second");
    emp2.setLastName("Employee");
    emp2.setEmail("emp2@gmail.com");
    emp2.setId(2);
    
    List<Employee> employees = new ArrayList<>();
    employees.add(emp1);
    employees.add(emp2);
    
    when(employeeService.getAllEmployees()).thenReturn(employees);
    
    ResultActions resultActions = mvc.perform(
        get("/").contentType(MediaType.APPLICATION_JSON));
    
    // Check the status
    resultActions.andExpect(status().isOk());
    // Check the json object
    resultActions.andExpect(status().is(HttpStatus.OK.value()));
    
    resultActions.andExpect(view().name("index"));
    
    resultActions.andExpect(model().attribute("listEmployees", hasSize(2)));
    
    
    resultActions.andExpect(model().attribute("listEmployees", hasItem(
        allOf(
            hasProperty("id", is(1L)),
            hasProperty("firstName", is("First")),
            hasProperty("lastName", is("Employee")),
            hasProperty("email", is("emp1@gmail.com"))
            )
        )));
    
    resultActions.andExpect(model().attribute("listEmployees", hasItem(
        allOf(
            hasProperty("id", is(2L)),
            hasProperty("firstName", is("Second")),
            hasProperty("lastName", is("Employee")),
            hasProperty("email", is("emp2@gmail.com"))
            )
        )));
  }
  
  @Test
  public void testShowNewEmployeeForm() throws Exception {
    ResultActions resultActions = mvc.perform(
        get("/showNewEmployeeForm").contentType(MediaType.APPLICATION_JSON));
    
    // Check the status
    resultActions.andExpect(status().isOk());
    // Check the json object
    resultActions.andExpect(status().is(HttpStatus.OK.value()));
    
    resultActions.andExpect(view().name("new_employee"));
    resultActions.andExpect(model().attributeExists("employee"));
  }
  
  @Test
  public void testSaveEmployee() throws Exception {
    Employee employee = new Employee();
    employee.setFirstName("First");
    employee.setLastName("Last");
    employee.setEmail("email@email.com");
    
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonContent = objectMapper.writeValueAsString(new Employee());
    
    ResultActions resultActions = mvc.perform(post("/saveEmployee")
        .contentType(MediaType.APPLICATION_JSON).content(jsonContent));
    
    // Check the status
    resultActions.andExpect(status().isFound());
    // Check the json object
    resultActions.andExpect(status().is(HttpStatus.FOUND.value()));
    
    resultActions.andExpect(view().name("redirect:/"));
  }
  
  @Test
  public void testShowFormForUpdate() throws Exception {
    Employee employee = new Employee();
    employee.setFirstName("First");
    employee.setLastName("Last");
    employee.setEmail("emp@email.com");
    employee.setId(1);
    
    when(employeeService.getEmployeeById(1)).thenReturn(employee);
    
    ResultActions resultActions = mvc.perform(get("/showFormForUpdate/1")
        .contentType(MediaType.APPLICATION_JSON));
    
    // Check the status
    resultActions.andExpect(status().isOk());
    // Check the json object
    resultActions.andExpect(status().is(HttpStatus.OK.value()));
    
    resultActions.andExpect(view().name("update_employee"));
    
    resultActions.andExpect(model().attribute("employee", is(
        allOf(
            hasProperty("id", is(1L)),
            hasProperty("firstName", is("First")),
            hasProperty("lastName", is("Last")),
            hasProperty("email", is("emp@email.com"))
            )
        )));
  }
}