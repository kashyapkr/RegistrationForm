package com.kashyap.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kashyap.registration.service.UserService;
import com.kashyap.registration.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class RegistrationControler {
	
	private UserService userService;

	public RegistrationControler(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	
	@GetMapping
	public String ShowRegisterationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}
	
	@PostMapping
	public String RegisterUserAccount(@ModelAttribute("user")UserRegistrationDto dto) {
		userService.save(dto);
		return "redirect:/registration?success";
	}
	

}
