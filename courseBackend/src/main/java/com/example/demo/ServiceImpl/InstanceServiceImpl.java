package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.InstanceDTO;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Instance;
import com.example.demo.Exception.CourseNotFoundException;
import com.example.demo.Exception.InstanceAlreadyExistException;
import com.example.demo.Exception.InstanceNotFoundException;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.InstanceRepository;
import com.example.demo.Service.InstanceService;

@Service
public class InstanceServiceImpl implements InstanceService {
	
	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public InstanceDTO createInstance(InstanceDTO instanceDTO) {
		// TODO Auto-generated method stub
		Instance inst = new Instance();
		Optional<Instance> instance =instanceRepository.findByYearAndSemesterAndCourse_CourseId(instanceDTO.getYear(), instanceDTO.getSemester(), instanceDTO.getCourseId());
		if(instance.isEmpty()) {
			inst =DTOtoEntity(instanceDTO);
			 instanceRepository.save(inst);
		}else {
			
			throw new InstanceAlreadyExistException("Instance Already exist with same year and semester for course "+instanceDTO.getCourseId());
		}
		InstanceDTO dto =EntityToDTO(inst);
		return dto;
	}

	@Override
	public List<InstanceDTO> getInstanceByYearAndSemester(int year, String semester) {
		// TODO Auto-generated method stub
		List<InstanceDTO> instanceDTOs = new ArrayList<>();
		instanceRepository.findByYearAndSemester(year, semester).forEach(instance->{
			InstanceDTO dto = EntityToDTO(instance);
			instanceDTOs.add(dto);
		});
		if(instanceDTOs.size() ==0) {
			throw new InstanceNotFoundException("Instance Not Found for the selected year and semester !!!");
		}
		
		return instanceDTOs;
	}

	@Override
	public InstanceDTO getInstanceByYearSemesterAndCourseID(int year, String semester, int courseId) {
		// TODO Auto-generated method stub
		InstanceDTO instanceDTO= new InstanceDTO();
		Optional<Instance> instance =instanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId);
		if(instance.isPresent()) {
			instanceDTO =EntityToDTO(instance.get());
		}else {
			throw new InstanceNotFoundException("Instance not found !!!");
		}
		
		return instanceDTO;
	}

	@Override
	public String DeleteInstanceByYearSemesterAndCourseID(int year, String semester, int courseId) {
		// TODO Auto-generated method stub
		Optional<Instance> instance =instanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId);
		if(instance.isPresent()) {
			instanceRepository.delete(instance.get());
		}else {
			throw new InstanceNotFoundException("Instance Not Found to delete !!!!");
		}
		return "Instance Delete SuccessFully";
	}

	@Override
	public InstanceDTO EntityToDTO(Instance instance) {
		// TODO Auto-generated method stub
		InstanceDTO instanceDTO = new InstanceDTO();
		instanceDTO.setInstanceId(instance.getInstanceId());
		instanceDTO.setYear(instance.getYear());
		instanceDTO.setSemester(instance.getSemester());
		instanceDTO.setCourseId(instance.getCourse().getCourseId());
		return instanceDTO;
	}

	@Override
	public Instance DTOtoEntity(InstanceDTO instanceDTO) {
		// TODO Auto-generated method stub
		Instance instance = new Instance();
		instance.setInstanceId(instanceDTO.getInstanceId());
		instance.setYear(instanceDTO.getYear());
		instance.setSemester(instanceDTO.getSemester());
		Optional<Course> course =courseRepository.findById(instanceDTO.getCourseId());
		if(course.isPresent()) {
			instance.setCourse(course.get());
		}else {
			throw new CourseNotFoundException("Course Not Found with courseId "+instanceDTO.getCourseId());
			
		}
		
		return instance;
	}

}
