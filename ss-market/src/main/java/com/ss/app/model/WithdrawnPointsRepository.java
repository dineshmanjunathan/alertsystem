package com.ss.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.WithdrawnPoints;

@Service
public interface WithdrawnPointsRepository extends CrudRepository<WithdrawnPoints, Long> {
	List<WithdrawnPoints> findByIdAndStatus(Long id,String status);
	List<WithdrawnPoints> findByStatus(String status);
}