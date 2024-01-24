package com.fernando.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fernando.Entities.User;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;

	// CONSTRUCTOR

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	// FindAll
	public List<User> findAll() {
		return repository.findAll();
	}

	// FindById
	public User findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Create
	public User create(User user) {
		if (user == null)
			throw new RequiredObjectIsNullException();
		return repository.save(user);
	}

	// Update
	public User update(User user) {
		if (user == null)
			throw new RequiredObjectIsNullException();

		User entity = repository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setFullName(user.getFullName());
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		return repository.save(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUserName(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username" + username + " Not Found!");
		}
	}
}
