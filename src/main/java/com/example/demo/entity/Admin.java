package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Admin {

	@Id
	private String adminName;

	private String adminPassword;

	@OneToMany
	private List<Schedule> appointments = new ArrayList<>();

	// Required by Hibernate
	public Admin() {
	}

	public Admin(String adminName, String adminPassword) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public List<Schedule> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Schedule> appointments) {
		this.appointments = appointments;
	}
}