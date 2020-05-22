package ru.job4j.carsplase.models;

import java.util.StringJoiner;
/**
 * Engine
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.04.2020
 */
public class Engine {
    private int id;
    private String name;

    public String getName() {
        return this.name;
    }

    public Engine setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return this.id;
    }

    public Engine setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Engine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
