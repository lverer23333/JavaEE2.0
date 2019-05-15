package edu.bjtu.ee4j.services;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.domain_second.Course;

public interface CourseService {
	Page <Course> findAll(Pageable pageable);
    Course getCourseById(Integer id);
    Course saveCourse(Course Course);
	void deleteCourse(Integer id);
	public Course getCourse(String Course_name);
	


  
	
}
