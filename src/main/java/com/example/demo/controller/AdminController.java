package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AdminRepository;
import com.example.demo.dao.ScheduleRepo;

@Controller
public class AdminController {
	
	AdminRepository adminRepo;
	
	@Autowired
	ScheduleRepo scheduleRepo;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login/admin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	/* To Delete Appointment*/
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable("id") long id, Model m) {
////		Schedule s=service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid userid "+id));
//		service.delete(id);
//		return "/index";
//	}
	
	@PostMapping("/login")
	public String loginCheck(@RequestParam String AdminName, @RequestParam String AdminPassword) {
		if(AdminName.equals("Admin") && AdminPassword.equals("12345")) {
			return "index";
		}
		return null;
	}


}
