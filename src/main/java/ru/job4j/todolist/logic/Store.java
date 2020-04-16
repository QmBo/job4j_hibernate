package ru.job4j.todolist.logic;

import java.util.List;

/**
 * Store
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 10.04.2020
 */
public interface Store<T> {
    T add(T element);
    T update(T element);
    T delete(T element);
    List<T> findAll();
    T findById(T element);
}
