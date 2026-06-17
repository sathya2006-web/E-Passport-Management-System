package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PassportRepo;
import com.example.demo.entity.Passport;
@Service
public class PassportService {

	@Autowired
	private PassportRepo passportRepository;

	public Passport registerPassport(Passport passport) {
		return passportRepository.save(passport);
	}

	public List<Passport> getAllPassports() {
		return passportRepository.findAll();
	}

	public Passport getPassportByPassportNumber(String passportNumber) {
		return passportRepository.findById(passportNumber)
				.orElse(null);
	}

	public Passport updatePassport(Passport passport) {
		return passportRepository.save(passport);
	}

	public void deletePassport(String passportNumber) {
		passportRepository.deleteById(passportNumber);
	}
	public long countByStatus(ApplicationStatus status){
		return passportRepository.countByStatus(status);
	}
}