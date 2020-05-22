package ru.job4j.carsplace.models;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * BodyName
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
@Entity
@Table(name = "body")
public class BodyName {
    private int id;
    private String name;

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public BodyName setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Column
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public BodyName setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BodyName.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
