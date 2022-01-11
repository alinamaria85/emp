package com.sample.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity object for the "employees" table.
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee {
	
  /**
   * The employee id.
   */
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	/**
	 * The employee first name.
	 */
	@Column(name = "first_name")
	private String firstName;
	
	/**
	 * The employee last name.
	 */
	@Column(name = "last_name")
	private String lastName;
	
	/**
	 * The employee email.
	 */
	@Column(name = "email")
	private String email;
	
	/**
   * The employee role.
   */
  @Column(name = "role")
	private String role;
}
