package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Request;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
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
    public Page<User> getRequests(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findAll(pageable);
    }
    public void update(User user) {
        // Check if the user exists
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null for update");
        }

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user.getId()));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        userRepository.save(existingUser);
    }

    public Map<String, Long> getRoleDistribution() {
        Map<String, Long> roleCounts = new HashMap<>();

        long userCount = userRepository.countByRole("user");
        long companyAdminCount = userRepository.countByRole("admin_company");

        roleCounts.put("user", userCount);
        roleCounts.put("admin_company", companyAdminCount);

        return roleCounts;
    }
}
