package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.Service.CourseService;

import jakarta.validation.Valid;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<CourseDTO> AddCourse(@Valid @RequestBody CourseDTO courseDTO){
			CourseDTO dto =courseService.saveCourse(courseDTO);
		return new  ResponseEntity<CourseDTO>(dto, HttpStatus.CREATED);	
	}
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> listCourses (){
		List<CourseDTO> courses = courseService.getAllCourses();
		return new ResponseEntity<List<CourseDTO>>(courses,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> getCourseFromId(@PathVariable int id){
			CourseDTO course = courseService.getCourseById(id);
		return new ResponseEntity<CourseDTO>(course,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCoursebyID(@PathVariable int id){
		String s =courseService.deleteCourseById(id);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
}
