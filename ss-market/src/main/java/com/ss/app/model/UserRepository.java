package com.ss.app.model;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Member;

@Service
public interface UserRepository extends CrudRepository<Member, String>{
		
	Optional<Member> findById(String id);
	
	Optional<Member> findByIdAndRole(String id,String role);
	
	Optional<Member> findByReferencecode(String id);
	
	List<Member> findByReferedby(String id);
	
	List<Member> findByReferedbyAndRole(String id,String role);
	
	@Query(value="select referencecode from t_member m where m.referencecode=:id", nativeQuery=true)
	String checkSponserExists(String id);
	
	Optional<Member> findByIdAndPasswordAndRole(String id, String password, String role);
	
	@Transactional
	void deleteById(String id);
	
	@Query(value="select * from t_member m where m.active_days  > CURRENT_TIMESTAMP ", nativeQuery=true)
	List<Member> getActiveMembers();
	
	@Query(value="select * from t_member m where m.active_days  > CURRENT_TIMESTAMP and m.role='MEMBER'", nativeQuery=true)
	List<Member> getActiveMembersOnly();
	
	@Query(value="select case when active_days is not null and active_days > CURRENT_TIMESTAMP THEN 'ACTIVE' ELSE 'INACTIVE' END member_status,* from t_member ", nativeQuery=true)
	List<Member> getAllMemberWithStatus();
	
	

}