package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Override;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.Entity.Course;
import com.example.demo.Exception.CourseNotFoundException;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Service.CourseService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	public CourseRepository courseRepository;

	@Override
	public CourseDTO saveCourse(CourseDTO courseDTO) {
		// TODO Auto-generated method stub
		Course course =DTOtoEntity(courseDTO);
		courseRepository.save(course);
		CourseDTO dto =EntityTodto(course);
		return dto;
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courses = courseRepository.findAll();
		
		List<CourseDTO> coursesDTOs = new ArrayList<>();
		for(Course course :courses) {
			CourseDTO courseDTO = EntityTodto(course);
			coursesDTOs.add(courseDTO);
		}
		return coursesDTOs;
	}

	@Override
	public CourseDTO getCourseById(int id) {
		// TODO Auto-generated method stub
		Optional<Course> course = courseRepository.findById(id);
		CourseDTO courseDTO = new CourseDTO();
		if(course.isPresent()) {
			courseDTO =EntityTodto(course.get());
		}else {
			throw new CourseNotFoundException("Course not found with courseId "+id);
		}
		return courseDTO;
	}

	@Override
	public String deleteCourseById(int id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
		return "Course Deleted Successfully";
	}

	@Override
	public CourseDTO EntityTodto(@Valid Course course) {
		// TODO Auto-generated method stub
		CourseDTO courseDto = new CourseDTO();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setCourseTitle(course.getCourseTitle());
		courseDto.setCourseCode(course.getCourseCode());
		courseDto.setCourseDescription(course.getCourseDescription());
		courseDto.setInstances(course.getInstances());
		return courseDto;
	}

	@Override
	public Course DTOtoEntity(@Valid CourseDTO courseDTO) {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setCourseId(courseDTO.getCourseId());
		course.setCourseTitle(courseDTO.getCourseTitle());
		course.setCourseCode(courseDTO.getCourseCode());
		course.setCourseDescription(courseDTO.getCourseDescription());
		course.setInstances(courseDTO.getInstances());
		return course;
	}

}
