package edu.bjtu.ee4j.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import edu.bjtu.ee4j.domain.Person;
import edu.bjtu.ee4j.domain_second.Coach;

public interface PersonService {
    Iterable<Person> getAllPersons();
    Person getPersonById(Integer id);
    Person savePerson(Person person);
	void deletePerson(Integer id);

	public String getUser(String email);
	public String getUser1(String phone);

	
  
	
}
