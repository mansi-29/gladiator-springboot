package com.lti.myproj.controller;
import com.lti.myproj.model.User;

import com.lti.myproj.service.RegistrationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins="http://localhost:4200")

public class ProjectController {
    
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception 
	{   
		String tempEmailId = user.getEmailId();
		int tempId = user.getId();
		if((tempEmailId!=null && !"".equals(tempEmailId)))
		{
		User userobj = service.fetchUserByEmailId(tempEmailId);
			if(userobj != null)
				throw new Exception("User with this "+ tempEmailId+"already exists");
			
			 
		}
		
		if(tempId != '\0')
		{
			User u = service.fetchUserById(tempId);
			if(u != null)
					throw new Exception("User with this "+ tempId+"already exists");
		}
		
		
		
		
		
		User userobj = null;
		service.saveUser(user);
		return userobj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception
	{
		String tempEmailId = user.getEmailId();
		String tempPass=user.getPassword();
		User userObj =null;
		if(tempEmailId!=null && tempPass!=null )
		{
		userObj  = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if(userObj==null)
		{
			throw new Exception("bad credentials");
		}
		return userObj;
	}
}
