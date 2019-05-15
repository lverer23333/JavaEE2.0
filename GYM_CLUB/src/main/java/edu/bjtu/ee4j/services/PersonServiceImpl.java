package edu.bjtu.ee4j.services;

import edu.bjtu.ee4j.domain.Person;
import edu.bjtu.ee4j.repository.primary.ContactRepository;

import org.hibernate.query.Query;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.util.concurrent.RateLimiter;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
    private ContactRepository contactRepository;
	private RateLimiter rateLimiter = RateLimiter.create(1); // rate is "10 permits per second"
	
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
    	rateLimiter.acquire();
        this.contactRepository = contactRepository;
    }
    
    @Override
    public Iterable<Person> getAllPersons() {
    	rateLimiter.acquire();
        return this.contactRepository.findAll();
    }
    
    @Override
    public Person getPersonById(Integer id) {
    	rateLimiter.acquire();
        return (Person) this.contactRepository.findById(id).orElse(null);
    }
    
    @Override
    public Person savePerson(Person person) {
    	rateLimiter.acquire();
        return (Person) this.contactRepository.save(person);
    }
    
    @Override
    public void deletePerson(Integer id) {
    	rateLimiter.acquire();
        this.contactRepository.deleteById(id);
    }

   

	@Override
	public String getUser(String email) {
		// TODO Auto-generated method stub
		rateLimiter.acquire();
		
		       return  this.contactRepository.getByPasswordAndUsername(email);
		 
		   
	}

	@Override
	public String getUser1(String phone) {
		// TODO Auto-generated method stub
		
		       return  this.contactRepository.getByPasswordAndUsername1(phone);
		 
		   
	}

	

    
}
