package com.application.repository;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.application.model.Admin1;
import com.application.model.Professor;

public interface Admin1Repository extends CrudRepository<Admin1, String>
{

public Admin1 findByEmail(String email);
    
	
	public List<Admin1> findAdminListByEmail(String email);
	
	public Admin1 findByEmailAndPassword(String email, String password);
	
	public List<Admin1> findProfileByEmail(String email);
	
//	public Admin1 findByAdminname(String username);
	
	@Transactional
	@Modifying
	@Query(value = "update professor set status = 'accept' where email = ?1", nativeQuery = true)
	public void updateStatus(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update professor set status = 'reject' where email = ?1", nativeQuery = true)
	public void rejectStatus(String email);


//	public Admin1 findByAdminname(String username);

}
