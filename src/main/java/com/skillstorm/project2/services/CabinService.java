package com.skillstorm.project2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.models.Cabin;
import com.skillstorm.project2.repositories.CabinRepository;

@Service
public class CabinService {
	
	private CabinRepository cabinRepo;
	
	@Autowired
	public CabinService(CabinRepository cabinRepo)
	{
		this.cabinRepo = cabinRepo;
	}
	
	public List<Cabin> getAllCabins()
	{
		return cabinRepo.findAll();
	}
	
	public Cabin getCabinById(long id)
	{
		return cabinRepo.findById(id).get();
	}
	
	public List<Cabin> getCabinByStateId(String stateId)
	{
		return cabinRepo.findByStateId(stateId);
	}

}
