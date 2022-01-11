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
  SOFTWARE_DEVELOPER_JUNIOR("SOFTWARE_DEVELOPER_JUNIOR"),
  /**
   * Software developer senior.
   */
  SOFTWARE_DEVELOPER_SENIOR("SOFTWARE_DEVELOPER_SENIOR"),
  /**
   * Quality assurance junior.
   */
  QUALITY_ASSURANCE_JUNIOR("QUALITY_ASSURANCE_JUNIOR"),
  /**
   * Quality assurance senior.
   */
  QUALITY_ASSURANCE_SENIOR("QUALITY_ASSURANCE_SENIOR"),
  /**
   * Technical writer.
   */
  TECHNICAL_WRITER("TECHNICAL_WRITER"),
  /**
   * System administrator.
   */
  SYSTEM_ADMINISTRATOR("SYSTEM_ADMINISTRATOR");
  
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
