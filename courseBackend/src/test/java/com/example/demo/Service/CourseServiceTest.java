package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.Entity.Course;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.ServiceImpl.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
	
	@Mock
	private CourseRepository courseRepository;
	
	@InjectMocks
	private CourseServiceImpl courseService;
	
	private CourseDTO courseDTO;
	
	private Course course;
	List<Course> courses;
	
	@BeforeEach
	public void setup() {
		course = new Course();
		course.setCourseTitle("Introduction to web development");
		course.setCourseCode("CS101");
		course.setCourseDescription("This course provide basics of web development ");
		
		courses = new ArrayList<>();
		Course course1 = new Course();
		course1.setCourseTitle("Introduction to Java");
		course1.setCourseCode("CS102");
		course1.setCourseDescription("This course provide basics of Java development ");
		
		Course course2=new Course();
		course2.setCourseTitle("Introduction to Python development");
		course2.setCourseCode("CS103");
		course2.setCourseDescription("This course provide basics of Python development ");
		courses.add(course);
		courses.add(course1);
		courses.add(course2);
		
		
	}
	
	@Test
	public void saveCourseTest() {
		when(courseRepository.save(any(Course.class))).thenReturn(course);
		courseDTO = courseService.EntityTodto(course);
		CourseDTO savedCourseDto = courseService.saveCourse(courseDTO);
		
		assertEquals("CS101", savedCourseDto.getCourseCode());
		verify(courseRepository,times(1)).save(course);
	}
	
	@Test
	public void TestgetCourseByID() {
		when(courseRepository.findById(1)).thenReturn(Optional.of(course));
		courseDTO = courseService.getCourseById(1);
		assertEquals(course.getCourseCode(), courseDTO.getCourseCode());
		
	}
	
	@Test
	public void testgetAllcourses () {
		when(courseRepository.findAll()).thenReturn(courses);
		List<CourseDTO> dtos =courseService.getAllCourses();
		assertEquals(dtos.size(), courses.size());
	}
	
}
