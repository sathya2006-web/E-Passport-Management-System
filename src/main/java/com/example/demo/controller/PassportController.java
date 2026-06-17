package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.CitizenRepo;
import com.example.demo.dao.PassportRepo;
import com.example.demo.entity.ApplicationStatus;
import com.example.demo.entity.Citizen;
import com.example.demo.entity.Passport;
import com.example.demo.service.PassportService;

@Controller
public class PassportController {

	@Autowired
	private PassportService passportService;

	@Autowired
	private PassportRepo passportRepo;

	@Autowired
	private CitizenRepo citizenRepo;

	/*
	 * PASSPORT APPLICATION FORM
	 */

	@GetMapping("/register/form")
	public ModelAndView showRegistrationPage() {
		return new ModelAndView("register");
	}

	/*
	 * APPLY FOR PASSPORT
	 */

	@PostMapping("/register")
	public ModelAndView processApplication(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("appointmentDate") String appointmentDate,
			@RequestParam("supportingDocs") MultipartFile supportingDocs,
			@RequestParam("registrationNumber") String registrationNumber) {

		ModelAndView modelAndView = new ModelAndView();

		Optional<Citizen> citizenOptional =
				citizenRepo.findByRegistrationNumber(registrationNumber);

		/*
		 * INVALID REGISTRATION NUMBER
		 */
		if (citizenOptional.isEmpty()) {

			modelAndView.addObject(
					"error",
					"Invalid Registration Number");

			modelAndView.setViewName("register");

			return modelAndView;
		}

		/*
		 * DOCUMENT NOT UPLOADED
		 */
		if (supportingDocs.isEmpty()) {

			modelAndView.addObject(
					"error",
					"Please upload supporting documents");

			modelAndView.setViewName("register");

			return modelAndView;
		}

		try {

			Citizen citizen = citizenOptional.get();

			/*
			 * PREVENT DUPLICATE PASSPORT APPLICATIONS
			 */
			if (citizen.getPassport() != null) {

				modelAndView.addObject(
						"error",
						"Passport already exists for Registration Number: "
								+ registrationNumber);

				modelAndView.setViewName("register");

				return modelAndView;
			}

			byte[] bytes = supportingDocs.getBytes();

			Passport passport =
					new Passport(
							name,
							email,
							appointmentDate,
							bytes,
							citizen);

			citizen.setPassport(passport);

			passportService.registerPassport(passport);

			/* Optional but recommended */
			citizenRepo.save(citizen);

			modelAndView.addObject(
					"message",
					passport.getPassportNumber());

			modelAndView.addObject(
					"status",
					passport.getStatus());

		} catch (Exception e) {

			modelAndView.addObject(
					"error",
					"Error registering passport: "
							+ e.getMessage());
		}

		modelAndView.setViewName("register1");

		return modelAndView;
	}

	/*
	 * CHANGE APPOINTMENT FORM
	 */


	@GetMapping("/changeAppointment")
	public String changeAppointmentForm() {

		return "changeAppointment";
	}

	/*
	 * CHANGE APPOINTMENT
	 */

	@PostMapping("/changeAppointmentTime")
	public String changeAppointment(
			@RequestParam("passportNumber") String passportNumber,
			@RequestParam("appointmentDate") String appointmentDate,
			Model model) {

		Optional<Passport> passport =
				passportRepo.findById(passportNumber);

		if (passport.isPresent()) {

			Passport p = passport.get();

			p.setAppointmentDate(appointmentDate);

			passportRepo.save(p);

			model.addAttribute(
					"success",
					"Appointment updated successfully");

			return "Userindex";
		}

		model.addAttribute(
				"error",
				"Passport not found");

		return "changeAppointment";
	}

	/*
	 * VIEW APPLICATION PAGE
	 */
	@GetMapping("/ViewApplication")
	public String viewApplication() {

		return "ViewApplication";
	}

	@GetMapping("/admin/applications")
	public String viewApplication(Model model) {

		model.addAttribute("applications",
				passportService.getAllPassports());

		model.addAttribute("total",
				passportRepo.count());

		model.addAttribute("applied",
				passportService.countByStatus(ApplicationStatus.APPLIED));

		model.addAttribute("verification",
				passportService.countByStatus(ApplicationStatus.UNDER_VERIFICATION));

		model.addAttribute("approved",
				passportService.countByStatus(ApplicationStatus.APPROVED));

		model.addAttribute("rejected",
				passportService.countByStatus(ApplicationStatus.REJECTED));

		return "admin-applications";
	}
	/*
	 * VIEW APPLICATION DETAILS
	 */
	@PostMapping("/ViewApplicationdetails")
	public String viewApplicationDetails(
			@RequestParam("passportNumber") String passportNumber,
			Model model) {

		Optional<Passport> passport =
				passportRepo.findById(passportNumber);

		if (passport.isPresent()) {

			model.addAttribute(
					"details",
					passport.get());

			return "ViewForm";
		}

		model.addAttribute(
				"error",
				"Application not found");

		return "ViewApplication";
	}

	/*
	 * ==============================
	 * ADMIN APPROVAL WORKFLOW
	 * ==============================
	 */


	/*
	 * UNDER VERIFICATION
	 */

	@GetMapping("/admin/applications/{id}/verify")
	public String verifyApplication(
			@PathVariable String id) {

		Passport passport =
				passportService.getPassportByPassportNumber(id);

		if (passport != null) {

			passport.setStatus(
					ApplicationStatus.UNDER_VERIFICATION);

			passportService.updatePassport(passport);
		}

		return "redirect:/admin/applications";
	}

	/*
	 * APPROVE
	 */

	@GetMapping("/admin/applications/{id}/approve")
	public String approveApplication(
			@PathVariable String id) {

		Passport passport =
				passportService.getPassportByPassportNumber(id);

		if (passport != null) {

			passport.setStatus(
					ApplicationStatus.APPROVED);

			passportService.updatePassport(passport);
		}

		return "redirect:/admin/applications";
	}

	/*
	 * REJECT
	 */

	@GetMapping("/admin/applications/{id}/reject")
	public String rejectApplication(
			@PathVariable String id) {

		Passport passport =
				passportService.getPassportByPassportNumber(id);

		if (passport != null) {

			passport.setStatus(
					ApplicationStatus.REJECTED);

			passportService.updatePassport(passport);
		}

		return "redirect:/admin/applications";
	}
}