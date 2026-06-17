package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String date;

	private String time;

	private String description;

	private String passportNumber;

	public Schedule() {
	}

	public Schedule(String date,
					String time,
					String description,
					String passportNumber) {

		this.date = date;
		this.time = time;
		this.description = description;
		this.passportNumber = passportNumber;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
}