package com.alert.app.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.alert.app.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
	
	@Transactional
	Notification findFirstByDeliveryStatus(String DeliveryStatus);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE t_notification set delivery_status ='Y',success_count=:successCount where id =:id ", nativeQuery = true)
	int updateDeliveryStatus(@Param("successCount") long successCount,@Param("id") long id);
	
	
}
