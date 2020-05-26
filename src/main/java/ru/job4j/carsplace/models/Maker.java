package ru.job4j.carsplace.models;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Maker
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Maker implements KeyValue {
    private int id;
    private String name;
    private Set<Model> allModels;

    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Maker setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Maker setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets all models.
     *
     * @return the all models
     */
    public Set<Model> getAllModels() {
        return this.allModels;
    }

    /**
     * Sets all models.
     *
     * @param allModels the all models
     * @return the all models
     */
    public Maker setAllModels(Set<Model> allModels) {
        this.allModels = allModels;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Maker.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
