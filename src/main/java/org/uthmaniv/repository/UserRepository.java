package org.uthmaniv.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.uthmaniv.model.ToDo;
import org.uthmaniv.model.User;
import org.uthmaniv.service.Response;

import java.io.IOException;

public class UserRepository {
    private final BidiMap<String, User> users = new DualHashBidiMap<>();
    private final ArrayListValuedHashMap<String, ToDo> userTodos = new ArrayListValuedHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();


    public String registerUser(String email, String password) throws IOException {
        Preconditions.checkNotNull(email, "Email cannot be null");
        Preconditions.checkNotNull(password, "Password cannot be null");
        Preconditions.checkArgument(!email.isEmpty(), "Email cannot be empty");
        Preconditions.checkArgument(!password.isEmpty(), "Password cannot be empty");
        Preconditions.checkArgument(!users.containsKey(email), "Email already exists");

        User user = new User(email, password);
        users.put(email, user);
        return objectMapper.writeValueAsString(new Response("success", "User registered successfully"));
    }

    public String loginUser(String email, String password) throws IOException {
        Preconditions.checkNotNull(email, "Email cannot be null");
        Preconditions.checkNotNull(password, "Password cannot be null");

        User user = users.get(email);
        Preconditions.checkArgument(user != null && user.getPassword().equals(password), "Invalid credentials");

        return objectMapper.writeValueAsString(new Response("success", "Login successful"));
    }

    public String updatePassword(String email, String oldPassword, String newPassword) throws IOException {
        Preconditions.checkNotNull(email, "Email cannot be null");
        Preconditions.checkNotNull(oldPassword, "Old password cannot be null");
        Preconditions.checkNotNull(newPassword, "New password cannot be null");
        Preconditions.checkArgument(!newPassword.isEmpty(), "New password cannot be empty");
        Preconditions.checkArgument(users.containsKey(email), "User does not exist");

        User user = users.get(email);
        Preconditions.checkArgument(user.getPassword().equals(oldPassword), "Incorrect old password");

        user.setPassword(newPassword);
        return objectMapper.writeValueAsString(new Response("success", "Password updated successfully"));
    }
}
