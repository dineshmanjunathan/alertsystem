package com.ss.app.model;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByOrderNumber(Long orderNumber);
}
