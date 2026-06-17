package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CitizenRepo;
import com.example.demo.entity.Citizen;

@Controller
public class CitizenController {

	@Autowired
	private CitizenRepo repo;

	/*
	 * CITIZEN REGISTRATION FORM
	 */
	@GetMapping("/UserRegistration/form")
	public String showRegisterForm(Citizen citizen) {
		return "RegisterCitizen";
	}

	/*
	 * CITIZEN REGISTRATION
	 */
	@PostMapping("/UserRegistration")
	public String register(Citizen citizen,
						   BindingResult result,
						   Model model) {

		if (result.hasErrors()) {
			return "RegisterCitizen";
		}

		repo.save(citizen);

		model.addAttribute(
				"RegistrationNumber",
				citizen.getRegistrationNumber());

		return "UserRegistered";
	}

	/*
	 * USER LOGIN PAGE
	 */
	@GetMapping("/login/user")
	public String loginPage() {
		return "Userlogin";
	}

	/*
	 * USER LOGIN
	 */
	@PostMapping("/Userlogin")
	public String loginUser(
			@RequestParam("registrationNumber")
			String registrationNumber,

			@RequestParam("password")
			String password,

			Model model) {

		Optional<Citizen> citizen =
				repo.findByRegistrationNumber(registrationNumber);

		if (citizen.isPresent()
				&& citizen.get()
				.getPassword()
				.equals(password)) {

			model.addAttribute(
					"citizen",
					citizen.get());

			return "Userindex";
		}

		model.addAttribute(
				"error",
				"Invalid Registration Number or Password");

		return "Userlogin";
	}

	/*
	 * FORGOT PASSWORD PAGE
	 */
	@GetMapping("/forgotPassword")
	public String forgotPasswordForm() {
		return "forgotPassword";
	}

	/*
	 * RESET PASSWORD
	 */
	@PostMapping("/forgot")
	public String forgotPassword(
			@RequestParam("registrationNumber")
			String registrationNumber,

			@RequestParam("password")
			String password,

			Model model) {

		Optional<Citizen> citizen =
				repo.findByRegistrationNumber(registrationNumber);

		if (citizen.isPresent()) {

			Citizen existingCitizen = citizen.get();

			existingCitizen.setPassword(password);

			repo.save(existingCitizen);

			model.addAttribute(
					"success",
					"Password updated successfully");

			model.addAttribute(
					"citizen",
					existingCitizen);

			return "Userindex";
		}

		model.addAttribute(
				"error",
				"Registration Number not found");

		return "forgotPassword";
	}

	/*
	 * USER DASHBOARD
	 */
	@GetMapping("/user")
	public String userDashboard() {
		return "Userindex";
	}
}