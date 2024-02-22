package com.application.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Chapter;
import com.application.model.Enrollment;
import com.application.model.Professor;
import com.application.model.User;
import com.application.model.Wishlist;
import com.application.services.ChapterService;
import com.application.services.CourseService;
import com.application.services.EnrollmentService;
import com.application.services.ProfessorService;
import com.application.services.UserService;
import com.application.services.WishlistService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
public class UserController 
{
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/userlist")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public ResponseEntity<List<User>> getUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/userprofileDetails/{email}")
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public ResponseEntity<List<User>> getProfileDetails(@PathVariable String email) throws Exception
	{
		List<User> users = userService.fetchProfileByEmail(email);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
//	@CrossOrigin(origins = "http://onlinetutorialsystem.netlify.app")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public ResponseEntity<User> updateUserProfile(@RequestBody User user) throws Exception
	{
		User userobj = userService.updateUserProfile(user);
		return new ResponseEntity<User>(userobj, HttpStatus.OK);
	}
	
	@GetMapping("/gettotalusers")
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public ResponseEntity<List<Integer>> getTotalUsers() throws Exception
	{
		List<User> users = userService.getAllUsers();
		List<Integer> usersCount = new ArrayList<>();
		usersCount.add(users.size());
		return new ResponseEntity<List<Integer>>(usersCount, HttpStatus.OK);
	}
	
	
	@PostMapping("/upuser")
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
	public User registerUser(@RequestBody User user) throws Exception
	{
		String currEmail = user.getEmail();
				
		if(currEmail != null || !"".equals(currEmail))
		{
			User userObj = userService.fetchUserByEmail(currEmail);

		}
		User userObj = null;
		userObj = userService.saveUser(user);
		return userObj;
	}
}
