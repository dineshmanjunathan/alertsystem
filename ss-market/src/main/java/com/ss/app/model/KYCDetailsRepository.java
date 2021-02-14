package com.ss.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.KYCDetails;

public interface KYCDetailsRepository extends CrudRepository<KYCDetails, String> {
	
	KYCDetails findByMemberId(String memberId);

}
