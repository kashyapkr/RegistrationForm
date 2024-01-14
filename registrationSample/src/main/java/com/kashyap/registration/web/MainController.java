package com.kashyap.registration.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kashyap.registration.model.User;
import com.kashyap.registration.service.UserService;

@Controller
public class MainController {
	
	private UserService service;
	
	@GetMapping("/login")
	public String login() {
		return "login";
		}
	
	@PostMapping("/login")
	public String logedin() {
		return "redirect:/home";
	}
	@GetMapping("/")
	public String home() {
		return "home";
			}
	@GetMapping("/about")
	public String AboutPage() {
		return "about";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> user = service.findAllUsers();
		model.addAttribute("users", user);
		return "userList";
		
	}

}
