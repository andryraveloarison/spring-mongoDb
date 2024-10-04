package com.ajtech.repo;

import com.ajtech.dto.UserResponseDto;
import com.ajtech.entity.User;
import com.ajtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    // Sauvegarder un utilisateur dans MongoDB
    public User save(User user) {
        return userRepository.save(user);
    }

    // Récupérer tous les utilisateurs et les mapper en DTO
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userList = new ArrayList<>();
        for (User user : users) {
            UserResponseDto userResponse = new UserResponseDto(user.getId(), user.getEmail(), user.getName(), user.getStatus());
            userList.add(userResponse);
        }
        return userList;
    }

    // Récupérer tous les utilisateurs pour la connexion
    public List<User> findAllForLogin() {
        return userRepository.findAll();
    }

    // Rechercher un utilisateur par ID
    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // Rechercher un utilisateur par email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Supprimer un utilisateur par ID
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted!";
    }
}
