package com.example.demo.entity;

import java.util.concurrent.ThreadLocalRandom;

import jakarta.persistence.*;

@Entity
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String address;

	private String phoneNumber;

	@Column(unique = true)
	private String registrationNumber;

	@OneToOne(mappedBy = "citizen",
			cascade = CascadeType.ALL)
	private Passport passport;

	public Citizen() {
	}

	public Citizen(String firstName,
				   String lastName,
				   String email,
				   String password,
				   String address,
				   String phoneNumber) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	@PrePersist
	public void generateRegistrationNumber() {

		int randomNumber =
				ThreadLocalRandom.current()
						.nextInt(1000000, 9999999);

		this.registrationNumber =
				"REG" + String.format("%07d", randomNumber);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public String getPassportNumber() {

		if (passport == null) {
			return null;
		}

		return passport.getPassportNumber();
	}
}