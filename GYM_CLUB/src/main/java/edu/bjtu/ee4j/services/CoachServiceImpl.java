package edu.bjtu.ee4j.services;

import edu.bjtu.ee4j.domain_second.Coach;
import edu.bjtu.ee4j.repository.secondary.CoachRepository;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

@Service
public class CoachServiceImpl implements CoachService {
	@Autowired
    private CoachRepository coachRepository;
	private RateLimiter rateLimiter = RateLimiter.create(1); // rate is "10 permits per second"
	
    @Autowired
    public void setcoachRepository(CoachRepository coachRepository) {
    	rateLimiter.acquire();
        this.coachRepository = coachRepository;
    }
    
    @Override
    public Page<Coach> getAllCoaches(Pageable pageable) {
    	rateLimiter.acquire();
        return (Page<Coach>) this.coachRepository.findAll(pageable);
    }
    
    @Override
    public Coach getCoachById(Integer num_id) {
    	rateLimiter.acquire();
        return (Coach) this.coachRepository.findById(num_id).orElse(null);
    }
    
    @Override
    public Coach saveCoach(Coach Coach) {
    	rateLimiter.acquire();
        return (Coach) this.coachRepository.save(Coach);
    }
    
    @Override
    public void deleteCoach(Integer num_id) {
    	rateLimiter.acquire();
        this.coachRepository.deleteById(num_id);
    }

   

	@Override
	public Coach getCoach(String coach_name) {
		// TODO Auto-generated method stub
		
		       return  this.coachRepository.getByCoach_Name(coach_name);
		 
		   
	}

	


    
}
