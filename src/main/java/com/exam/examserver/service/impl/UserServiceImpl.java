package com.exam.examserver.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.repository.RoleRepository;
import com.exam.examserver.repository.UserRepository;
import com.exam.examserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		User local = this.userRepository.findByUsername(user.getUsername());

		if (local != null) {
			System.out.println("User already exists!!");
			throw new Exception("User already exsists!!");
		} else {
			// create user
			for (UserRole ur : userRoles) {
				this.roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}

		return local;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if (local != null) {
			local.setUsername(user.getUsername());
			local.setEmail(user.getEmail());
			local.setFirstName(user.getFirstName());
			local.setLastName(user.getLastName());
			local.setPassword(user.getPassword());
			local.setPhone(user.getPhone());
			local.setProfile(user.getProfile());
			
			this.userRepository.save(local);
			
			return local;
		}
		
		throw new Exception("User does not exists!");
	}

}
