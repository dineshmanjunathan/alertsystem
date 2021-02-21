package com.ss.app.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ss.app.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

	@Transactional
	void deleteByMember_Id(String id);
	
	@Transactional
	List<Notification> findByDeliveryStatusAndType(String deliveryStatus,String type);
}
