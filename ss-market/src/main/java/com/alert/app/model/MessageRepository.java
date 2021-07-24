package com.alert.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.alert.app.entity.Message;

@Service
public interface MessageRepository extends CrudRepository<Message, String> {


}