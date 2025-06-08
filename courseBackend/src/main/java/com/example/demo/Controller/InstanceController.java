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

import com.example.demo.DTO.InstanceDTO;
import com.example.demo.Service.InstanceService;

import jakarta.validation.Valid;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/api/instance")
public class InstanceController {
	
	@Autowired
	private InstanceService instanceService;
	
	
	@PostMapping("/save")
	public ResponseEntity<InstanceDTO> saveInstance(@Valid @RequestBody InstanceDTO instanceDTO) {
		
		instanceService.createInstance(instanceDTO);
		return new ResponseEntity<InstanceDTO>(instanceDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/{year}/{semester}")
	public ResponseEntity<List<InstanceDTO>> getInstancesWithYearAndSemester(@PathVariable int year, @PathVariable String semester){
		List<InstanceDTO> DTOs =instanceService.getInstanceByYearAndSemester(year, semester);
		return new ResponseEntity<List<InstanceDTO>>(DTOs,HttpStatus.OK);
	}
	
	@GetMapping("/{year}/{semester}/{courseId}")
	public ResponseEntity<InstanceDTO> getInstancesWithYearAndSemesterAndCourseId(@PathVariable int year, 
			@PathVariable String semester,@PathVariable int courseId){
		InstanceDTO dto = instanceService.getInstanceByYearSemesterAndCourseID(year, semester, courseId);
		return new ResponseEntity<InstanceDTO>(dto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{year}/{semester}/{courseId}")
	public ResponseEntity<String> deleteInstance(@PathVariable int year,@PathVariable String semester,@PathVariable int courseId){
		String s =instanceService.DeleteInstanceByYearSemesterAndCourseID(year, semester, courseId);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
}
