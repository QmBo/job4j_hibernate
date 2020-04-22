package ru.job4j.carsplase.models;


import javax.persistence.*;
import java.util.StringJoiner;

/**
 * Driver
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.04.2020
 */
@Entity
@Table(name = "driver")
public class Driver {
    private int id;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public Driver setId(int id) {
        this.id = id;
        return this;
    }
    @Column
    public String getName() {
        return this.name;
    }

    public Driver setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Driver.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
