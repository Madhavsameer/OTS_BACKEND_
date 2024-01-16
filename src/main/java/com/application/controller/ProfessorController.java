package com.application.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.application.model.Course;
import com.application.model.Professor;
import com.application.model.User;
import com.application.model.Wishlist;
import com.application.services.ChapterService;
import com.application.services.CourseService;
import com.application.services.ProfessorService;
import com.application.services.WishlistService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})

public class ProfessorController 
{	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private WishlistService wishlistService;
	
	@GetMapping("/professorlist")
//	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Professor>> getProfessorList() throws Exception
	{
		List<Professor> professors = professorService.getAllProfessors();
		return new ResponseEntity<List<Professor>>(professors, HttpStatus.OK);
	}
	
	@GetMapping("/youtubecourselist")
//	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Course>> getYoutubeCourseList() throws Exception
	{
		List<Course> youtubeCourseList = courseService.fetchByCoursetype("Youtube");

		return new ResponseEntity<List<Course>>(youtubeCourseList, HttpStatus.OK);
	}
	
	@GetMapping("/websitecourselist")
//	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Course>> getWebsiteCourseList() throws Exception
	{
		List<Course> websiteCourseList = courseService.fetchByCoursetype("Website");
		return new ResponseEntity<List<Course>>(websiteCourseList, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@PostMapping("/addCourse")
	@CrossOrigin(origins = "http://localhost:3000")
	public Course addNewCourse(@RequestBody Course course) throws Exception
	{
		Course courseObj = null;
		String newID = getNewID();
		course.setCourseid(newID);
		
		courseObj = courseService.addNewCourse(course);
		return courseObj;
	}
	
	
	
	
	@GetMapping("/gettotalcourses")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Integer>> getTotalCourses() throws Exception
	{
		List<Course> courses = courseService.getAllCourses();
		List<Integer> coursesCount = new ArrayList<>();
		coursesCount.add(courses.size());
		return new ResponseEntity<List<Integer>>(coursesCount, HttpStatus.OK);
	}
	
	
  
  @GetMapping("/getcoursenames")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Course>> getCourseNames() throws Exception
	{
		List<Course> courses = courseService.getAllCourses();

		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	public String getNewID()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) 
        {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
	}
	
}
