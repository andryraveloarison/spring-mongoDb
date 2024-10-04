package com.ajtech.repository;

import com.ajtech.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    // Vous pouvez définir des méthodes personnalisées ici, par exemple :
    List<Message> findByReceiverIdOrSenderId(String receiverId, String senderId);
}
