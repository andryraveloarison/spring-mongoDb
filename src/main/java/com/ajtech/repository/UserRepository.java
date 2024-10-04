package com.ajtech.repository;

import com.ajtech.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Méthode personnalisée pour trouver un utilisateur par email
    User findByEmail(String email);
}
