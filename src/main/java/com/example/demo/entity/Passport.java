package com.example.demo.entity;

import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.*;

@Entity
public class Passport {

	@Id
	private String passportNumber;

	private String name;

	private String email;

	private String appointmentDate;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	@OneToOne
	@JoinColumn(name = "citizen_id", unique = true)
	private Citizen citizen;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] supportingDocs;

	public Passport() {
	}

	public Passport(String name,
					String email,
					String appointmentDate,
					byte[] supportingDocs,
					Citizen citizen) {

		this.name = name;
		this.email = email;
		this.appointmentDate = appointmentDate;
		this.supportingDocs = supportingDocs;
		this.citizen = citizen;
	}

	@PrePersist
	private void initializePassport() {

		int randomNumber =
				ThreadLocalRandom.current()
						.nextInt(1000000, 9999999);

		this.passportNumber =
				"PA" + String.format("%07d", randomNumber);

		this.status =
				ApplicationStatus.APPLIED;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public byte[] getSupportingDocs() {
		return supportingDocs;
	}

	public void setSupportingDocs(byte[] supportingDocs) {
		this.supportingDocs = supportingDocs;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
}