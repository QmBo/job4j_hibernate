package ru.job4j.carsplace.models;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Model
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Model implements KeyValue {
    private int id;
    private String name;
    private Maker maker;
    private Set<Generation> generations;

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
    public Model setId(int id) {
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
    public Model setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets maker.
     *
     * @return the maker
     */
    public Maker getMaker() {
        return this.maker;
    }

    /**
     * Sets maker.
     *
     * @param maker the maker
     * @return the maker
     */
    public Model setMaker(Maker maker) {
        this.maker = maker;
        return this;
    }

    /**
     * Gets generations.
     *
     * @return the generations
     */
    public Set<Generation> getGenerations() {
        return this.generations;
    }

    /**
     * Sets generations.
     *
     * @param generations the generations
     * @return the generations
     */
    public Model setGenerations(Set<Generation> generations) {
        this.generations = generations;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Model.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("maker='" + maker + "'")
                .toString();
    }
}
