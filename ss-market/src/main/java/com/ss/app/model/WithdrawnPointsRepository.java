package com.ss.app.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.WithdrawnPoints;

@Service
public interface WithdrawnPointsRepository extends CrudRepository<WithdrawnPoints, String>{
		

}