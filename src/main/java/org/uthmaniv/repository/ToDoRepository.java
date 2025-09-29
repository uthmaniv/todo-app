package org.uthmaniv.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.uthmaniv.model.User;
import org.uthmaniv.utils.Status;
import org.uthmaniv.model.ToDo;
import org.uthmaniv.service.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoRepository {

    private final ArrayListValuedHashMap<String, ToDo> userTodos = new ArrayListValuedHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String addTodo(User user, String title, String description) throws IOException {
        Preconditions.checkNotNull(title, "Title cannot be null");
        Preconditions.checkArgument(!title.isEmpty(), "Title cannot be empty");

        ToDo todo = new ToDo(title, description);
        userTodos.put(user.getEmail(), todo);
        return objectMapper.writeValueAsString(new Response("success", "Todo added successfully", todo));
    }

    public String updateTodoDescription(User user,String title, String description) throws IOException {
        String userEmail = user.getEmail();
        List<ToDo> todos = Lists.newArrayList(userTodos.get(userEmail));
        ToDo todo = todos.stream()
                .filter(t -> t.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        if (description != null) {
            todo.setDescription(description);
        }
        userTodos.put(user.getEmail(), todo);
        return objectMapper.writeValueAsString(new Response("success", "Todo updated successfully", todo));
    }

    public String updateTodoStatus (User user, String title, Status status) throws IOException {
        List<ToDo> todos = Lists.newArrayList(userTodos.get(user.getEmail()));
        ToDo toDo = todos.stream()
                .filter(t -> t.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        toDo.setStatus(status);
        userTodos.put(user.getEmail(), toDo);
        return objectMapper.writeValueAsString(new Response("Success", "Todo updated Successfully", toDo));
    }

    public String getAllTodos(User user) throws IOException {
        List<ToDo> todos = Lists.newArrayList(userTodos.get(user.getEmail()));
        return objectMapper.writeValueAsString(new Response("success", "Todos retrieved successfully", todos));
    }

    public String getActiveTodos(User user) throws IOException {
        List<ToDo> todos = Lists.newArrayList(userTodos.get(user.getEmail()));
        List<ToDo> activeTodos = CollectionUtils.select(todos, todo -> todo.getStatus() == Status.ACTIVE, new ArrayList<>());
        return objectMapper.writeValueAsString(new Response("success", "Active todos retrieved successfully", activeTodos));
    }

    public String getCompletedTodos(String email) throws IOException {
        Preconditions.checkNotNull(email, "Email cannot be null");

        List<ToDo> todos = Lists.newArrayList(userTodos.get(email));
        List<ToDo> completedTodos = CollectionUtils.select(todos, todo -> todo.getStatus() == Status.COMPLETED, new ArrayList<>());
        return objectMapper.writeValueAsString(new Response("success", "Completed todos retrieved successfully", completedTodos));
    }

    public String searchTodos(User user, String searchTerm) throws IOException {
        Preconditions.checkNotNull(searchTerm, "Search term cannot be null");

        List<ToDo> todos = Lists.newArrayList(userTodos.get(user.getEmail()));
        List<ToDo> matchingTodos = todos.stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
        return objectMapper.writeValueAsString(new Response("success", "Search results retrieved successfully", matchingTodos));
    }

    public String deleteTodo(User user, String title) throws IOException {
        Preconditions.checkNotNull(title, "To-do title cannot be null");

        List<ToDo> todos = Lists.newArrayList(userTodos.get(user.getEmail()));
        ToDo todo = todos.stream()
                .filter(t -> t.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        userTodos.remove(todo);
        return objectMapper.writeValueAsString(new Response("success", "Todo deleted successfully"));
    }
}
