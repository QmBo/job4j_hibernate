package ru.job4j.carsplace.models;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Body
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Body implements KeyValue {
    private int id;
    private BodyName bodyName;
    private String name;
    private Generation generation;
    private Doors doors;
    private Engine engine;
    private Set<Engine> allEngines;
    private Set<Doors> allDoors;

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
    public Body setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets body name.
     *
     * @return the body name
     */
    public BodyName getBodyName() {
        return this.bodyName;
    }

    /**
     * Sets body name.
     *
     * @param bodyName the body name
     * @return the body name
     */
    public Body setBodyName(BodyName bodyName) {
        this.bodyName = bodyName;
        this.name = bodyName.getName();
        return this;
    }

    /**
     * Gets all doors.
     *
     * @return the all doors
     */
    public Set<Doors> getAllDoors() {
        return this.allDoors;
    }

    /**
     * Sets all doors.
     *
     * @param allDoors the all doors
     * @return the all doors
     */
    public Body setAllDoors(Set<Doors> allDoors) {
        this.allDoors = allDoors;
        return this;
    }

    /**
     * Gets doors.
     *
     * @return the doors
     */
    public Doors getDoors() {
        return this.doors;
    }

    /**
     * Sets doors.
     *
     * @param doors the doors
     * @return the doors
     */
    public Body setDoors(Doors doors) {
        this.doors = doors;
        return this;
    }

    /**
     * Gets engine.
     *
     * @return the engine
     */
    public Engine getEngine() {
        return this.engine;
    }

    /**
     * Sets engine.
     *
     * @param engine the engine
     * @return the engine
     */
    public Body setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    /**
     * Gets all engines.
     *
     * @return the all engines
     */
    public Set<Engine> getAllEngines() {
        return this.allEngines;
    }

    /**
     * Sets all engines.
     *
     * @param allEngines the all engines
     * @return the all engines
     */
    public Body setAllEngines(Set<Engine> allEngines) {
        this.allEngines = allEngines;
        return this;
    }

    /**
     * Gets generation.
     *
     * @return the generation
     */
    public Generation getGeneration() {
        return this.generation;
    }

    /**
     * Sets generation.
     *
     * @param generation the generation
     * @return the generation
     */
    public Body setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Body.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .add("doors=" + doors)
                .add("engine=" + engine)
                .toString();
    }
}
