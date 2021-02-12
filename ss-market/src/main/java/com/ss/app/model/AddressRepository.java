package com.ss.app.model;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByOrderNumber(Long orderNumber);
	
	@Transactional
	void deleteByMember_Id(String id);
}
