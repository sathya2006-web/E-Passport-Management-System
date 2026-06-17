package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ScheduleRepo;
import com.example.demo.entity.Schedule;

@Service
public class ScheduleSerice {
	
	@Autowired
	ScheduleRepo repo;
	
	public List<Schedule> findAll(){
		return repo.findAll();
	}
	
	public Optional<Schedule> findById(Long id) {
		return repo.findById(id);
	}
	
	public void save(Schedule schedule) {
		repo.save(schedule);
	}
	
	public void update(Schedule schedule) {
		Optional<Schedule> s=repo.findById(schedule.getId());
		if(s.isPresent()) {
			repo.save(schedule);
		}
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
