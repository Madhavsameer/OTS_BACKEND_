package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Admin1;
import com.application.model.Professor;
import com.application.model.User;
import com.application.services.Admin1Service;
import com.application.services.ProfessorService;
import com.application.services.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
public class LoginController 
{
	@Autowired
	private Admin1Service adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/")
    public String welcomeMessage()
    {
    	return "Welcome to Online Tutorial Management system !!!";
    }
	
	@PostMapping("/loginuser")
//	@CrossOrigin(origins = "https://onlinetutorialsystem.netlify.app")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public User loginUser(@RequestBody User user) throws Exception
	{
		String currEmail = user.getEmail();
		String currPassword = user.getPassword();
		
		User userObj = null;
		if(currEmail != null && currPassword != null)
		{
			userObj = userService.fetchUserByEmailAndPassword(currEmail, currPassword);
		}
		if(userObj == null)
		{
			throw new Exception("User does not exists!!! Please enter valid credentials...");
		}		
		return userObj;
	}
	
	@PostMapping("/loginprofessor")
//	@CrossOrigin(origins = "https://onlinetutorialsystem.netlify.app")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public Professor loginDoctor(@RequestBody Professor professor) throws Exception
	{
		String currEmail = professor.getEmail();
		String currPassword = professor.getPassword();
		
		Professor professorObj = null;
		if(currEmail != null && currPassword != null)
		{
			professorObj = professorService.fetchProfessorByEmailAndPassword(currEmail, currPassword);
		}
		if(professorObj == null)
		{
			throw new Exception("Professor does not exists!!! Please enter valid credentials...");
		}		
		return professorObj;
	}
	@PostMapping("/loginadmin")
//	@CrossOrigin(origins = "https://onlinetutorialsystem.netlify.app")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public Admin1 loginAdmin(@RequestBody Admin1 admin) throws Exception
	{
		String currEmail = admin.getEmail();
		String currPassword = admin.getPassword();
		System.out.println("Hii here comes the admin");
		Admin1 adminObj = null;
		if(currEmail != null && currPassword != null)
		{
			System.out.print("ad no pass :- "+currEmail);

			adminObj = adminService.fetchAdminByEmailAndPassword(currEmail, currPassword);
		}
		if(adminObj == null)
		{
			throw new Exception("admin does not exists!!! Please enter valid credentials...");
		}		
		System.out.print("Admin :- "+currEmail);
		return adminObj;
		
		
	}
	
}