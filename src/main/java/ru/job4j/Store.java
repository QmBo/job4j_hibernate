package ru.job4j;

import java.util.List;

/**
 * Store
 *
 * @param <T> the type parameter
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 10.04.2020
 */
public interface Store<T> {
    /**
     * Add t.
     *
     * @param element the element
     * @return the t
     */
    T add(T element);

    /**
     * Update t.
     *
     * @param element the element
     */
    void update(T element);

    /**
     * Delete t.
     *
     * @param element the element
     */
    void delete(T element);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find by id t.
     *
     * @param element the element
     * @return the t
     */
    T findById(T element);
}
