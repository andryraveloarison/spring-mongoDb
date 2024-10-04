package com.ajtech.repo;

import com.ajtech.entity.Message;
import com.ajtech.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    // Sauvegarder un message dans MongoDB
    public Message save(Message message) {
        message.setCreatedAt(LocalDateTime.now().toString());
        return messageRepository.save(message);
    }

    // Récupérer tous les messages
    public List<Message> findAll() {
        try {
            return messageRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch messages", e);
        }
    }

    // Rechercher un message par ID
    public Message findMessageById(String id) {
        return messageRepository.findById(id).orElse(null);
    }

    // Supprimer un message par ID
    public String deleteMessage(String id) {
        messageRepository.deleteById(id);
        return "Message deleted!";
    }

    // Rechercher les messages d'un utilisateur par son ID (Receiver ou Sender)
    public List<Message> findMessagesByUserId(String userId) {
        return messageRepository.findByReceiverIdOrSenderId(userId, userId);
    }

    // Rechercher la conversation entre deux utilisateurs
    public List<Message> findConversation(String currentUserId, String userId) {
        List<Message> messages = messageRepository.findByReceiverIdOrSenderId(currentUserId, userId);
        return messages.stream()
                .filter(message -> (message.getReceiverId().equals(currentUserId) && message.getSenderId().equals(userId))
                        || (message.getReceiverId().equals(userId) && message.getSenderId().equals(currentUserId)))
                .collect(Collectors.toList());
    }
}
