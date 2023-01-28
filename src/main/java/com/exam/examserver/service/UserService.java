package com.exam.examserver.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;

@Service
public interface UserService {
	
	//creating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user
	public User getUser(String username);
	
	//delete user
	public void deleteUser(long id);
	
	//update user
	public User updateUser(User user) throws Exception;

}
