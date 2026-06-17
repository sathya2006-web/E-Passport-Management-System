package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Long>{
	Optional<Citizen> findByRegistrationNumber(String registrationNumber);
}
