package com.ss.app.model;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.KYCDetails;

public interface KYCDetailsRepository extends CrudRepository<KYCDetails, String> {
	
	@Transactional
	KYCDetails findByMemberId(String memberId);

}
