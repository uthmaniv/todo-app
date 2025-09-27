package org.uthmaniv.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class ToDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private final Instant dateCreated;

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateCreated = Instant.now();
    }

    public String getTitle() {
        return title;
    }

    public ToDo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToDo setDescription(String description) {
        this.description = description;
        return this;
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
