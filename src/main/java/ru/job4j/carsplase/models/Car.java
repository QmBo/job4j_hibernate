package ru.job4j.carsplase.models;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;
/**
 * Car
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.04.2020
 */
@Entity
@Table(name = "car")
public class Car {
    private int id;
    private String name;
    private Engine engine;
    private Set<Driver> drivers;


    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    public Engine getEngine() {
        return this.engine;
    }

    public Car setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    @Column
    public String getName() {
        return this.name;
    }

    public Car setName(String name) {
        this.name = name;
        return this;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public Car setId(int id) {
        this.id = id;
        return this;
    }

    public Car setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "driver_id", nullable = false, updatable = false) })
    public Set<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("engine=" + engine)
                .add("drivers=" + drivers)
                .toString();
    }
}
