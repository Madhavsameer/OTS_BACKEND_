package com.application.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.application.model.Admin1;
import com.application.model.Professor;
import com.application.repository.Admin1Repository;

@Service
public class Admin1Service {
	@Autowired
	private Admin1Repository adminRepo;
	

	
	public Admin1 saveAdmin(Admin1 admin)
	{
		return adminRepo.save(admin);
	}
	
	public Admin1 addNewAdmin(Admin1 admin)
	{
		return adminRepo.save(admin);
	}
	
	public Admin1 updateAdminProfile(Admin1 admin)
	{
		return adminRepo.save(admin);
	}
	
	public List<Admin1> getAllAdmin()
	{
		return (List<Admin1>)adminRepo.findAll();
	}
	
	public List<Admin1> getAdminListByEmail(String email) 
	{
		return (List<Admin1>)adminRepo.findAdminListByEmail(email);
	}
	
	public Admin1 fetchAdminByEmail(String email)
	{
		return adminRepo.findByEmail(email);
	}
	
//	public Admin1 fetchAdminByAdminname(String username)
//	{
//		return adminRepo.findByAdminname(username);
//	}
	
	public Admin1 fetchAdminByEmailAndPassword(String email, String password)
	{
		return adminRepo.findByEmailAndPassword(email, password);
	}
	
	public List<Admin1> fetchProfileByEmail(String email)
	{
		return (List<Admin1>)adminRepo.findProfileByEmail(email);
	}

	public void updateStatus(String email) 
	{
		adminRepo.updateStatus(email);
	}

	public void rejectStatus(String email) 
	{
		adminRepo.rejectStatus(email);
	}

	public List<Admin1> getAdminByEmail(String email)
	{
		return adminRepo.findAdminListByEmail(email);
	}
}
