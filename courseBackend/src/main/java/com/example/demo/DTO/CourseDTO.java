package com.example.demo.DTO;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.example.demo.Entity.Instance;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CourseDTO {
	private int courseId;
	@NotBlank(message = "Please write the course title")
	@Length(min = 5,message = "Title should be more than 5 character !!")
	private String courseTitle;
	@NotBlank
	@Length(min = 5,message = "Code should at least contain 5 character !!")
	private String courseCode;
	@NotBlank
	@Length(min=10,message="Description should be more thane 10 character !!")
	private String courseDescription;
	private List<Instance> instances;
}
