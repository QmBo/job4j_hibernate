package ru.job4j.carsplace.models;

import javax.persistence.*;

/**
 * Photo
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
@Entity
@Table(name = "photos")
public class Photo {
    private int id;
    private Add add;
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
    public Photo setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets add.
     *
     * @return the add
     */
    @SuppressWarnings("JpaAttributeTypeInspection")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "photos_ref", joinColumns = {
            @JoinColumn(name = "photo_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ad_id", updatable = false)})
    public Add getAdd() {
        return this.add;
    }

    /**
     * Sets add.
     *
     * @param add the add
     * @return the add
     */
    public Photo setAdd(Add add) {
        this.add = add;
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
    public Photo setName(String name) {
        this.name = name;
        return this;
    }
}
