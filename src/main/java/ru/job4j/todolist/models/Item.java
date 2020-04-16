package ru.job4j.todolist.models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Item
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class Item {
    private int id;
    private String description;
    private boolean done;
    private Timestamp create;

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isDone() {
        return done;
    }

    public Item setDone(boolean done) {
        this.done = done;
        return this;
    }

    public Timestamp getCreate() {
        return create;
    }

    public Item setCreate(Timestamp create) {
        this.create = create;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && done == item.done
                && Objects.equals(description, item.description)
                && Objects.equals(create, item.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, done, create);
    }
}
