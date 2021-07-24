package com.alert.app.model;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.alert.app.entity.User;

@Service
public interface UserRepository extends CrudRepository<User, String>{
		
	Optional<User> findById(String id);
	
	@Transactional
	void deleteById(String id);
		
}