package ru.job4j.carsplace.models;

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
    private Maker maker;
    private Model model;
    private Generation generation;
    private Body body;
    private Doors doors;
    private Engine engine;
    private Drive drive;
    private GearBox gearBox;
    private Set<Driver> drivers;


    /**
     * Gets engine.
     *
     * @return the engine
     */
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    public Engine getEngine() {
        return this.engine;
    }

    /**
     * Sets engine.
     *
     * @param engine the engine
     * @return the engine
     */
    public Car setEngine(Engine engine) {
        this.engine = engine;
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
    public Car setName(String name) {
        this.name = name;
        return this;
    }

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
    public Car setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets drivers.
     *
     * @param drivers the drivers
     * @return the drivers
     */
    public Car setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }

    /**
     * Gets drivers.
     *
     * @return the drivers
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
            joinColumns = {@JoinColumn(name = "car_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "driver_id", nullable = false, updatable = false) })
    public Set<Driver> getDrivers() {
        return this.drivers;
    }


    /**
     * Gets maker.
     *
     * @return the maker
     */
    @ManyToOne
    @JoinColumn(name = "maker_id", foreignKey = @ForeignKey(name = "MAKER_ID_FK"))
    public Maker getMaker() {
        return this.maker;
    }

    /**
     * Sets maker.
     *
     * @param maker the maker
     * @return the maker
     */
    public Car setMaker(Maker maker) {
        this.maker = maker;
        return this;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    @ManyToOne
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    public Model getModel() {
        return this.model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     * @return the model
     */
    public Car setModel(Model model) {
        this.model = model;
        return this;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    @ManyToOne
    @JoinColumn(name = "body_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    public Body getBody() {
        return this.body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     * @return the body
     */
    public Car setBody(Body body) {
        this.body = body;
        return this;
    }

    /**
     * Gets drive.
     *
     * @return the drive
     */
    @ManyToOne
    @JoinColumn(name = "drive_id", foreignKey = @ForeignKey(name = "drive_id_fk"))
    public Drive getDrive() {
        return this.drive;
    }

    /**
     * Sets drive.
     *
     * @param drive the drive
     * @return the drive
     */
    public Car setDrive(Drive drive) {
        this.drive = drive;
        return this;
    }

    /**
     * Gets gear box.
     *
     * @return the gear box
     */
    @ManyToOne
    @JoinColumn(name = "gear_box_id", foreignKey = @ForeignKey(name = "gear_box_id_fk"))
    public GearBox getGearBox() {
        return this.gearBox;
    }

    /**
     * Sets gear box.
     *
     * @param gearBox the gear box
     * @return the gear box
     */
    public Car setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
        return this;
    }

    /**
     * Gets doors.
     *
     * @return the doors
     */
    @ManyToOne
    @JoinColumn(name = "doors_id", foreignKey = @ForeignKey(name = "doors_id_fk"))
    public Doors getDoors() {
        return this.doors;
    }

    /**
     * Sets doors.
     *
     * @param doors the doors
     * @return the doors
     */
    public Car setDoors(Doors doors) {
        this.doors = doors;
        return this;
    }

    /**
     * Gets generation.
     *
     * @return the generation
     */
    @ManyToOne
    @JoinColumn(name = "generation_id", foreignKey = @ForeignKey(name = "generation_id_fk"))
    public Generation getGeneration() {
        return this.generation;
    }

    /**
     * Sets generation.
     *
     * @param generation the generation
     * @return the generation
     */
    public Car setGeneration(Generation generation) {
        this.generation = generation;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("maker=" + maker)
                .add("model=" + model)
                .add("body=" + body)
                .add("drive=" + drive)
                .add("gearBox=" + gearBox)
                .add("doors=" + doors)
                .add("generation=" + generation)
                .add("engine=" + engine)
                .toString();
    }
}
