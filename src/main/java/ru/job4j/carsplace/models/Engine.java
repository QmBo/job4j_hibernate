package ru.job4j.carsplace.models;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Engine
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.04.2020
 */
public class Engine implements KeyValue {
    private int id;
    private String name;
    private Body body;
    private Drive drive;
    private Set<Drive> allDrives;
    private GearBox gearBox;
    private Set<GearBox> allGearBox;

    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Engine setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Engine setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     * @return the body
     */
    public Engine setBody(Body body) {
        this.body = body;
        return this;
    }

    /**
     * Gets drive.
     *
     * @return the drive
     */
    public Drive getDrive() {
        return this.drive;
    }

    /**
     * Sets drive.
     *
     * @param drive the drive
     * @return the drive
     */
    public Engine setDrive(Drive drive) {
        this.drive = drive;
        return this;
    }

    /**
     * Gets all drives.
     *
     * @return the all drives
     */
    public Set<Drive> getAllDrives() {
        return this.allDrives;
    }

    /**
     * Sets all drives.
     *
     * @param allDrives the all drives
     * @return the all drives
     */
    public Engine setAllDrives(Set<Drive> allDrives) {
        this.allDrives = allDrives;
        return this;
    }

    /**
     * Gets gear box.
     *
     * @return the gear box
     */
    public GearBox getGearBox() {
        return this.gearBox;
    }

    /**
     * Sets gear box.
     *
     * @param gearBox the gear box
     * @return the gear box
     */
    public Engine setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
        return this;
    }

    /**
     * Gets all gear box.
     *
     * @return the all gear box
     */
    public Set<GearBox> getAllGearBox() {
        return this.allGearBox;
    }

    /**
     * Sets all gear box.
     *
     * @param allGearBox the all gear box
     * @return the all gear box
     */
    public Engine setAllGearBox(Set<GearBox> allGearBox) {
        this.allGearBox = allGearBox;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Engine.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("body=" + body)
                .add("drive=" + drive)
                .add("gearBox=" + gearBox)
                .toString();
    }
}
