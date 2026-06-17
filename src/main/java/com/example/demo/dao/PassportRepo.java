package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Passport;

@Repository
public interface PassportRepo extends JpaRepository<Passport, String> {

	Passport findByPassportNumber(String passportNumber);
	long countByStatus(ApplicationStatus status);
}
