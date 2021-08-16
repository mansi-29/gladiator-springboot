package com.lti.myproj.repository;

import com.lti.myproj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RegistrationRepository extends JpaRepository<User,Integer> {
	
	public User findByEmailId(String emaiId);
	public User findByEmailIdAndPassword(String emailId, String password);
	public User findById(int id);
	

}
