package ru.job4j.carsplace.models;

import javax.persistence.*;
import java.util.Set;
import java.util.StringJoiner;

/**
 * GearBox
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
@Entity
@Table(name = "gear_box")
public class GearBox implements KeyValue {
    private int id;
    private String name;
    private Set<Engine> allEngines;

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
    public GearBox setId(int id) {
        this.id = id;
        return this;
    }

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
    public GearBox setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets all engines.
     *
     * @return the all engines
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "gear_box_ref", joinColumns = {@JoinColumn(name = "engine_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "gear_box_id", updatable = false, nullable = false)})
    public Set<Engine> getAllEngines() {
        return this.allEngines;
    }

    /**
     * Sets all engines.
     *
     * @param allEngines the all engines
     * @return the all engines
     */
    public GearBox setAllEngines(Set<Engine> allEngines) {
        this.allEngines = allEngines;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GearBox.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
