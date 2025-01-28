package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getAuthenticatedUser(HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("authenticatedUser");

        if (authenticatedUser == null) {
            throw new IllegalStateException("No authenticated user in session");
        }

        return authenticatedUser;
    }



    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
