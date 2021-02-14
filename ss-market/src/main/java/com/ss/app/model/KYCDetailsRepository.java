package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.KYCDetails;
import com.ss.app.entity.WithdrawnPoints;

public interface KYCDetailsRepository extends CrudRepository<KYCDetails, Long> {
	
	@Transactional
	KYCDetails findByMemberId(String memberId);

	@Transactional
	List<KYCDetails> findByStatus(String status);
}
