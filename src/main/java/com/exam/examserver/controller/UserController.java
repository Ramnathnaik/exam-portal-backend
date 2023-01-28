package com.exam.examserver.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		user.setProfile("default.png");
		
		//encrypt password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> userRoles = new HashSet<>();
		
		Role role = new Role(45L, "NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		userRoles.add(userRole);
		
		return userService.createUser(user, userRoles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
		return this.userService.getUser(username);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable long userId) {
		this.userService.deleteUser(userId);
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) throws Exception {
		return this.userService.updateUser(user);
	}
	
}
