package com.jobin.nhs.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.jobin.nhs.People;
import com.jobin.nhs.PeopleRepository;  
//defining the business logic  
@Service  
public class PeopleService {
	@Autowired  
	PeopleRepository PeopleRepository;  
	
	//getting all People record by using the method findaAll() of CrudRepository in the order of  
	//skill ranking
	public List<People> getAllPeople()   
	{  
	List<People> people = new ArrayList<People>();  
	PeopleRepository.findAll().forEach(people1 -> people.add(people1));  
	Collections.sort(people, new CustomComparator());
	return people;  
	}  
	
	
	
	//getting a specific record by using the method findById() of CrudRepository  
	public People getPeopleById(int id)   
	{  
	return PeopleRepository.findById((long) id).get();  
	}  
	
	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(People people)   
	{  
		people.setSkillRating(findRating(people.getSkillLevel()));
		PeopleRepository.save(people);  
	}
	
	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
	PeopleRepository.deleteById((long) id);  
	}  
	
	//updating a record  
	public void update(People People, int peopleId)   
	{  
	PeopleRepository.save(People);  
	}  
	
	public int findRating(String skillset)   
	{  
		int rating=0;
		switch(skillset) {
		case "Expert":
			rating= 1;
		case "Practitioner":
			rating= 2;
		case "Working":
			rating= 3;
		case "Awareness":
			rating= 4; 			
		}
		return rating;}
}
