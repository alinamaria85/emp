package com.sample.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.employee.model.Employee;

/**
 * Repository used to manage employees
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
