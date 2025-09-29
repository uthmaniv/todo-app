package org.uthmaniv.model;

import org.uthmaniv.utils.Status;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ToDo implements Serializable {

    private final String id;

    private String title;

    private String description;

    private final Instant dateCreated;

    private Status status;

    public ToDo(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.dateCreated = Instant.now();
        this.status = Status.ACTIVE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(id, toDo.id) && Objects.equals(getTitle(), toDo.getTitle()) && Objects.equals(getDescription(), toDo.getDescription()) && Objects.equals(getDateCreated(), toDo.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTitle(), getDescription(), getDateCreated());
    }
}
