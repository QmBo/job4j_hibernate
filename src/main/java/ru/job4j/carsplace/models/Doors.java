package ru.job4j.carsplace.models;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * Doors
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
@Entity
@Table(name = "doors")
public class Doors implements KeyValue {
    private int id;
    private String name;
    private Body body;

    @Override
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
    public Doors setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    @Column(name = "numbers")
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public Doors setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets body gen id.
     *
     * @return the body gen id
     */
    @SuppressWarnings("JpaAttributeTypeInspection")
    @ManyToOne
    @JoinColumn(name = "body_gen_id", foreignKey = @ForeignKey(name = "BODY_GEN_ID_FK"))
    public Body getBodyGenId() {
        return this.body;
    }

    /**
     * Sets body gen id.
     *
     * @param bodyGenId the body gen id
     * @return the body gen id
     */
    public Doors setBodyGenId(Body bodyGenId) {
        this.body = bodyGenId;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Doors.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
