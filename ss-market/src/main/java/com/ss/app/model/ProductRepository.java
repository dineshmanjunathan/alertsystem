package com.ss.app.model;

import java.util.List;

import javax.persistence.OrderBy;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Product;

@Service
public interface ProductRepository extends CrudRepository<Product, String> {

	List<Product> findByCategory(String Category);

	@Transactional
	Product findByCode(String Code);
	
	@Transactional
	List<Product> findAllByOrderByCode();
	
	@Transactional
	Long deleteByCode(String code);
}