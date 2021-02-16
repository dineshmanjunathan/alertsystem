package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.StockPointPurchase;

@Service
public interface StockPointPurchaseRepository extends CrudRepository<StockPointPurchase, String> {
	
	@Transactional
	List<StockPointPurchase> findByStockPointId(String memberId);
	
	List<StockPointPurchase> findByOrderNumber(Long orderNumber);

}