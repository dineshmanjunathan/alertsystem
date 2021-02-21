package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.StockPointProduct;

@Service
public interface StockPointProuctRepository extends CrudRepository<StockPointProduct, String> {
	
	StockPointProduct findByCode(String Code);
	
	StockPointProduct findByCodeAndMemberId(String code,String memberId);
	
	List<StockPointProduct> findByMemberIdOrderByCode(String memberId);
	
	/*
	 * @Query(
	 * value="select sum(quantity) as quantity,price,member_id as memberId,code,prod_desc as prodDesc,status,category_code,max(image) as image from t_stock_point_product where status ='DELIVERED' group by price,member_id,code,prod_desc,status,category_code"
	 * , nativeQuery=true) List<StockPointProduct> findByTotalMemberIdAndStatus();
	 */
	
	@Transactional
	List<StockPointProduct> findByMemberId(String memberId);
	
	List<StockPointProduct> findByOrderNumber(Long orderNumber);
	
}