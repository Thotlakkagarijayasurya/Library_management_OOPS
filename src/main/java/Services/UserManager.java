package main.java.Services;

import main.java.entities.Role;
import main.java.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<Integer, User> userStore = new HashMap<>();
    private int userIdSequence = 1;
    private Integer currentUserId = null;  // Represents current user id

    // to register new user using name and role
    public User register(String name, Role role) {
        int newId = userIdSequence++;
        User user = new User(newId, name, role);
        userStore.put(newId, user);
        System.out.println("User registered successfully. Your user ID is: " + newId);
        return user;
    }

    // Login by user ID
    public boolean login(int userId) {
        if (userStore.containsKey(userId)) {
            currentUserId = userId;
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("no user found.");
            return false;
        }
    }

    // Logout
    public void logout() {
        if (currentUserId != null) {
            System.out.println("log out succesful.");
            currentUserId = null;
        } else {
            System.out.println("No user logged in.");
        }
    }

    // Get current user
    public User getCurrentUser() {
        if (currentUserId == null) return null;
        return userStore.get(currentUserId);
    }

    // View all users (for testing)
    public void listAllUsers() {
        if (userStore.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("\n=== Registered Users ===");
        for (User user : userStore.values()) {
            System.out.println(user);
        }
    }
}
