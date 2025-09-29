TODO Application

Overview

This is a Java-based TODO application that allows users to register, log in, and manage their TODO lists. The application is structured with separate repositories for user and TODO operations, using Guava for input validation and collections, Jackson for JSON serialization, and Apache Commons Collections for data storage and manipulation. Each class is in a separate file, with TodoApplication as the main entry point.

Project Structure





src/main/java/org.uthmaniv/model/User.java: Represents a user with email and password.



src/main/java/org.uthmaniv/model/ToDo.java: Represents a TODO item with title, description, creation date, and status.



src/main/java/org.uthmaniv/utils/Status.java: Enum for TODO status (ACTIVE, COMPLETED).



src/main/java/org.uthmaniv/utils/Response.java: Formats JSON responses.



src/main/java/org.uthmaniv/repository/UserRepository.java: Manages user operations (register, login, update password).



src/main/java/org.uthmaniv/repository/ToDoRepository.java: Manages TODO operations (add, update, delete, get, search).



src/main/java/org.uthmaniv/TodoApplication.java: Main entry point, coordinating between repositories.



pom.xml: Maven configuration with dependencies.

Features





User Management (in UserRepository):





- Register users with unique emails (registerUser)



- Login with email and password (loginUser)



- Update password (updatePassword)



TODO Management (in ToDoRepository):





- Add new TODOs (addTodo)



- Update TODOs (title, description, status) (updateTodo)



- Delete TODOs by title (deleteToDo)



- Retrieve all TODOs (getAllTodos)



- Filter active TODOs (getActiveTodos)



- Filter completed TODOs (getCompletedTodos)



- Search TODOs by title, description, or creation date (searchTodos)

Dependencies





Guava: Input validation (Preconditions) and collection utilities (Lists).



Jackson: JSON serialization with jackson-databind and jackson-datatype-jsr310 for Instant.



Apache Commons Collections: BidiMap for unique email mapping, MultiValuedMap for user-to-todos mapping, CollectionUtils for filtering.