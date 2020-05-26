package ru.job4j.carsplace.models;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Generation
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Generation implements KeyValue {
    private int id;
    private String name;
    private Model model;
    private int startYear;
    private int endYear;
    private boolean production;
    private Set<Body> allBody;

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
    public Generation setId(int id) {
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
    public Generation setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public Model getModel() {
        return this.model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     * @return the model
     */
    public Generation setModel(Model model) {
        this.model = model;
        return this;
    }

    /**
     * Gets start year.
     *
     * @return the start year
     */
    public int getStartYear() {
        return this.startYear;
    }

    /**
     * Sets start year.
     *
     * @param startYear the start year
     * @return the start year
     */
    public Generation setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    /**
     * Gets end year.
     *
     * @return the end year
     */
    public int getEndYear() {
        return this.endYear;
    }

    /**
     * Sets end year.
     *
     * @param endYear the end year
     * @return the end year
     */
    public Generation setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    /**
     * Is production boolean.
     *
     * @return the boolean
     */
    public boolean isProduction() {
        return this.production;
    }

    /**
     * Sets production.
     *
     * @param production the production
     * @return the production
     */
    public Generation setProduction(boolean production) {
        this.production = production;
        return this;
    }

    /**
     * Gets all body.
     *
     * @return the all body
     */
    public Set<Body> getAllBody() {
        return this.allBody;
    }

    /**
     * Sets all body.
     *
     * @param allBody the all body
     * @return the all body
     */
    public Generation setAllBody(Set<Body> allBody) {
        this.allBody = allBody;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Generation.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("model=" + model)
                .add("startYear=" + startYear)
                .add("endYear=" + endYear)
                .add("production=" + production)
                .toString();
    }
}

