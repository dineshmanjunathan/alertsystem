package com.ss.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Purchase;

@Service
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

	List<Purchase> findByMemberid(String memberid);
	
	List<Purchase> findByOrderStatus(String orderStatus);
	
	
	@Query(value = "SELECT * FROM t_purchase where exists (select t_member.id from t_member where t_member.role=?1 and t_member.id=memberid) ", nativeQuery = true)

	List<Purchase> findByMember(String role);
	
	List<Purchase> findByOrderNumber(Long orderNumber);	

}