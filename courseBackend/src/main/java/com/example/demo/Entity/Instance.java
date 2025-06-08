package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name ="instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instanceId;
	private int year;
	private String semester;
	@ManyToOne
	@JoinColumn(name="courseId")
	@JsonBackReference
	private Course course;

}
