package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.Entity.Course;

public interface CourseService {
	
	public CourseDTO saveCourse(CourseDTO courseDTO);
	public List<CourseDTO> getAllCourses();
	public CourseDTO getCourseById(int id);
	public String deleteCourseById(int id);
	
	public CourseDTO EntityTodto (Course course);
	public Course DTOtoEntity(CourseDTO courseDTO);
}
