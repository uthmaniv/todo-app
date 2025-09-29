package org.uthmaniv;


import org.uthmaniv.model.User;
import org.uthmaniv.repository.ToDoRepository;
import org.uthmaniv.repository.UserRepository;

import java.io.IOException;

public class TodoApplication {
    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;

    public TodoApplication() {
        this.userRepository = new UserRepository();
        this.toDoRepository = new ToDoRepository();
    }

    // User Management
    public String registerUser(String email, String password) throws IOException {
        return userRepository.registerUser(email, password);
    }

    public String loginUser(String email, String password) throws IOException {
        return userRepository.loginUser(email, password);
    }

    public String updatePassword(String email, String oldPassword, String newPassword) throws IOException {
        return userRepository.updatePassword(email, oldPassword, newPassword);
    }

    // TODO Management
    public String addTodo(User user, String title, String description) throws IOException {
        return toDoRepository.addTodo(user, title, description);
    }

    public String updateTodo(User user, String title,String description) throws IOException {
        return toDoRepository.updateTodoDescription(user,title, description);
    }

    public String deleteTodo(User user, String title) throws IOException {
        return toDoRepository.deleteTodo(user, title);
    }

    public String getAllTodos(User user) throws IOException {
        return toDoRepository.getAllTodos(user);
    }

    public String getActiveTodos(User user) throws IOException {
        return toDoRepository.getActiveTodos(user);
    }

    public String getCompletedTodos(String email) throws IOException {
        if (!userRepository.userExists(email)) {
            throw new IllegalArgumentException("User does not exist");
        }
        return toDoRepository.getCompletedTodos(email);
    }

    public String searchTodos(User user, String searchTerm) throws IOException {
        return toDoRepository.searchTodos(user, searchTerm);
    }

    public static void main(String[] args) {

    }
}