package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO.InstanceDTO;
import com.example.demo.Entity.Instance;

public interface InstanceService {
	
	public InstanceDTO createInstance(InstanceDTO instanceDTO);
	public List<InstanceDTO> getInstanceByYearAndSemester(int year,String semester);
	public InstanceDTO getInstanceByYearSemesterAndCourseID(int year,String semester,int courseId);
	public String DeleteInstanceByYearSemesterAndCourseID(int year,String semester,int courseId);
	public InstanceDTO EntityToDTO(Instance instance);
	public Instance DTOtoEntity(InstanceDTO instanceDTO);
}
