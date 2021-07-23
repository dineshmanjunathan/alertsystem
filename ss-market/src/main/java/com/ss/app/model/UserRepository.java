package com.ss.app.model;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Member;

@Service
public interface UserRepository extends CrudRepository<Member, String>{
		
	Optional<Member> findById(String id);
	
	@Transactional
	void deleteById(String id);
		
}