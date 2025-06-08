package com.example.demo.DTO;



import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstanceDTO {
	
	private int instanceId;
	@NotNull(message = "Year is required")
	@Min(value = 2025, message = "Year must be in format YYYY and should be Present year or greater")
    @Max(value = 2999, message = "Year must be in format YYYY and should be Present year or greater")
	private int year;
	@NotBlank(message="Semester should not be empty")
	private String semester;
	@NotNull(message = "courseId is required")
	private int courseId;
}
