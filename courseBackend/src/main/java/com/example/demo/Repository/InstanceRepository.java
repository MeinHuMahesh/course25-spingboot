package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Instance;

public interface InstanceRepository extends JpaRepository<Instance, Integer> {
		public List<Instance>findByYearAndSemester(int year,String semester);
		public Optional<Instance> findByYearAndSemesterAndCourse_CourseId(int year,String semester,int courseId);
}
