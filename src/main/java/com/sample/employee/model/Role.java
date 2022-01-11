package com.sample.employee.model;

import lombok.Getter;

/**
 * The employee role.
 */
@Getter
public enum Role {
  
  /**
   * Software developer junior.
   */
  SOFTWARE_DEVELOPER_JUNIOR("Software Developer Junior"),
  /**
   * Software developer senior.
   */
  SOFTWARE_DEVELOPER_SENIOR("Software Developer Senior"),
  /**
   * Quality assurance junior.
   */
  QUALITY_ASSURANCE_JUNIOR("Quality Assurance Junior"),
  /**
   * Quality assurance senior.
   */
  QUALITY_ASSURANCE_SENIOR("Quality Assurance Senior"),
  /**
   * Technical writer.
   */
  TECHNICAL_WRITER("Technical Writer"),
  /**
   * System administrator.
   */
  SYSTEM_ADMINISTRATOR("System Administrator");
  
  /**
   * The role value.
   */
  private String employeeRole;
  
  /**
   * Constructor.
   * 
   * @param role The employee role.
   */
  private Role(String role) {
    this.employeeRole = role;
  }

}
