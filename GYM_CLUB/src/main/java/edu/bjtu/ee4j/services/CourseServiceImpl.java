package edu.bjtu.ee4j.services;

import edu.bjtu.ee4j.domain_second.Course;
import edu.bjtu.ee4j.repository.secondary.CourseRepository;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
    private CourseRepository CourseRepository;
	private RateLimiter rateLimiter = RateLimiter.create(1); // rate is "10 permits per second"
    
    @Autowired
    public void setCourseRepository(CourseRepository CourseRepository) {
    	rateLimiter.acquire();
        this.CourseRepository = CourseRepository;
    }
    
    @Override
    public Page <Course> findAll(Pageable pageable) {
    	rateLimiter.acquire();
        return (Page<Course>) this.CourseRepository.findAll(pageable);
    }
    
    @Override
    public Course getCourseById(Integer id) {
    	rateLimiter.acquire();
        return this.CourseRepository.findById(id).orElse(null);
    }
    
    @Override
    public Course saveCourse(Course Course) {
    	rateLimiter.acquire();
        return (Course) this.CourseRepository.save(Course);
    }
    
    @Override
    public void deleteCourse(Integer id) {
    	rateLimiter.acquire();
        this.CourseRepository.deleteById(id);
    }

   

	@Override
	public Course getCourse(String Course_name) {
		// TODO Auto-generated method stub
		rateLimiter.acquire();
		
		       return  this.CourseRepository.getByCourse_Name(Course_name);
		 
		   
	}

	


    
}
