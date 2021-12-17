package com.sample.employee.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sample.employee.dto.UserRegistrationDTO;
import com.sample.employee.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDTO registrationDto);
}
