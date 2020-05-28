package ru.job4j.carsplace.models;

import ru.job4j.hello.models.User;

import java.sql.Timestamp;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Add
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Add {
    private int id;
    private String name;
    private Car car;
    private String description;
    private long price;
    private long odd;
    private boolean placed;
    private User addOwner;
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
    private Set<Photo> allPhotos;
    private int year;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Add setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Add setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return this.car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     * @return the car
     */
    public Add setCar(Car car) {
        this.car = car;
        return this;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     * @return the description
     */
    public Add setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public long getPrice() {
        return this.price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     * @return the price
     */
    public Add setPrice(long price) {
        this.price = price;
        return this;
    }

    /**
     * Gets odd.
     *
     * @return the odd
     */
    public long getOdd() {
        return this.odd;
    }

    /**
     * Sets odd.
     *
     * @param odd the odd
     * @return the odd
     */
    public Add setOdd(long odd) {
        this.odd = odd;
        return this;
    }

    /**
     * Is placed boolean.
     *
     * @return the boolean
     */
    public boolean isPlaced() {
        return this.placed;
    }

    /**
     * Sets placed.
     *
     * @param placed the placed
     * @return the placed
     */
    public Add setPlaced(boolean placed) {
        this.placed = placed;
        return this;
    }

    /**
     * Gets add owner.
     *
     * @return the add owner
     */
    public User getAddOwner() {
        return this.addOwner;
    }

    /**
     * Sets add owner.
     *
     * @param addOwner the add owner
     * @return the add owner
     */
    public Add setAddOwner(User addOwner) {
        this.addOwner = addOwner;
        return this;
    }

    /**
     * Gets all photos.
     *
     * @return the all photos
     */
    public Set<Photo> getAllPhotos() {
        return this.allPhotos;
    }

    /**
     * Sets all photos.
     *
     * @param allPhotos the all photos
     * @return the all photos
     */
    public Add setAllPhotos(Set<Photo> allPhotos) {
        this.allPhotos = allPhotos;
        return this;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     * @return the create time
     */
    public Add setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    /**
     * Sets year.
     *
     * @param year the year
     * @return the year
     */
    public Add setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return this.year;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Add.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("car=" + car)
                .add("description='" + description + "'")
                .add("price=" + price)
                .add("odd=" + odd)
                .add("placed=" + placed)
                .add("addOwner=" + addOwner)
                .add("year=" + year)
                .toString();
    }
}
