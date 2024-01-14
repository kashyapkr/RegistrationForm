package com.kashyap.registration.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kashyap.registration.model.User;
import com.kashyap.registration.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto dto);
	List<User> findAllUsers(); 

}
