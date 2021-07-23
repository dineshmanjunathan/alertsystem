package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.EnterMessage;

@Service
public interface CategoryRepository extends CrudRepository<EnterMessage, String> {


}