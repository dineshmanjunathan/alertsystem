package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Product;

@Service
public interface ProductRepository extends CrudRepository<Product, String> {
	
	@Transactional
	List<Product> findByCategory(String category);

	@Transactional
	Product findByCode(String Code);
	
	@Transactional
	List<Product> findAllByOrderByCode();
	
	@Transactional
	Long deleteByCode(String code);
	
	@Transactional
	@Query(value="select * from t_product p where p.status = 'ACTIVE' order by p.code ", nativeQuery=true)
	List<Product> getActiveProducts();
		
	@Transactional
	@Modifying
	@Query(value="update t_product set status = 'INACTIVE' where code=:code ", nativeQuery=true)
	int updateToInactiveProduct(String code);
}