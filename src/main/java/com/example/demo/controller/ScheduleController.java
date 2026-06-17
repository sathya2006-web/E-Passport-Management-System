package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Schedule;
import com.example.demo.service.ScheduleSerice;

@Controller
@RequestMapping("/appointments")
public class ScheduleController {
	
	@Autowired
	private ScheduleSerice service;
	
	/* To Display Appointment List*/
	@GetMapping("/")
	public String index(Model model) {
		List<Schedule> appointments=service.findAll();
		model.addAttribute("appointments",appointments);
		return "view";
	}
	
	
	@GetMapping("/add/Form")
	public String showAddForm(Schedule s) {
		return "addAppointment";
	}
	
	/* To Add Appointment*/
	@PostMapping("/add")
	public String Add(Schedule s, BindingResult r, Model m) {

		System.out.println("Passport Number = " + s.getPassportNumber());

		if (r.hasErrors()) {
			return "addAppointment";
		}

		service.save(s);

		return "/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Schedule s= service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User id: " +id));
		model.addAttribute("schedule",s);
		return "updateAppointment";
	}
	
	/* To Update Appointment*/
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") long id,
						 Schedule s,
						 BindingResult r,
						 Model m) {

		if(r.hasErrors()) {
			return "updateAppointment";
		}

		service.save(s);

		return "redirect:/appointments/";
	}
	/* To Delete Appointment*/
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model m) {
//		Schedule s=service.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid userid "+id));
		service.delete(id);
		return "/index";
	}
	
//	@GetMapping("/delete/{id}")
//	public String deleteAppointment(@PathVariable("id") Long id) {
//	    service.deleteAppointmentById(id);
//	    return "redirect:/schedule/list";
//	}

}
