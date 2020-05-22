package ru.job4j.carsplace.models;

/**
 * Year
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Year implements KeyValue {
    private final int id;
    private final String name;

    /**
     * Instantiates a new Year.
     *
     * @param id   the id
     * @param name the name
     */
    public Year(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
