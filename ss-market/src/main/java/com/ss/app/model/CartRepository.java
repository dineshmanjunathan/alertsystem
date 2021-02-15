package com.ss.app.model;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ss.app.entity.Cart;

@Service
public interface CartRepository extends CrudRepository<Cart, Long> {

	List<Cart> findByMemberid(String memberid);

	Cart findByMemberidAndCode(String memberid, String prodCode);
	
	@Query(value = "select sum(amount * quantity) from t_cart where memberid =:memberId", nativeQuery = true)
	Double getCartTotal(String memberId);
	
	@Query(value = "select amount, quantity, shipping_charge from t_cart where memberid =:memberId", nativeQuery = true)
	Cart getPurchaseTotal(String memberId);
	
	Optional<Cart> findById(String memberid);

	@Transactional
	Long deleteByMemberid(String memberId);

	@Transactional
	Long deleteByCodeAndMemberid(String code, String memberId);

}
