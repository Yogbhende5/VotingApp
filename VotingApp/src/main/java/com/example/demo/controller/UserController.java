package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/register")
	public  boolean addUser(@RequestBody User user)
	{
		try {
			int userCount = userRepo.count(user.getUsername());
			System.out.println(userCount);
			if(userCount==0)
			  {
				System.out.println("hii");
				userRepo.save(user);
				return true;
			  }
			
				return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping("/login")
	public boolean loginUser(@RequestBody User user)
	{
		
		try {
			User DbUser = userRepo.findByUsername(user.getUsername());
			if(user.getUsername()!=null && user.getPassword()!=null)
			{
				if(DbUser.getUsername().equals(user.getUsername()) && DbUser.getPassword().equals(user.getPassword()))
				{
					System.out.println("hii");
					return true;
				}
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
			
		}
	}
	
	@GetMapping("/getUserById/{uId}")
	public User addUserAndPost(@PathVariable int uId)
	{
		try {
			return userRepo.findById(uId).get();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@GetMapping("/getAllUsers")
	public List<User> getAllUser()
	{
		try {
			return userRepo.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@GetMapping("/getUsernamePostTitleAndContent/{username}")
	public UserDto getUsernamePostTitleAndContent(@PathVariable String username )
	{
		try {
			UserDto userPostDetails = userRepo.userPostDetails(username);
			System.out.println(userPostDetails);
			return userPostDetails;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
