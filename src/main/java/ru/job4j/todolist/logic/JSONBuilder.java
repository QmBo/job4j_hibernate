package ru.job4j.todolist.logic;

import ru.job4j.todolist.models.Item;
/**
 * JSONBuilder
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class JSONBuilder {
    /**
     * Build JSON from Item.
     * @param item pars object
     * @return JSON string
     */
    public static String build(final Item item) {
        return String.format(
                "{\"desc\": \"%s\", \"id\": %d, \"done\": %b}",
                item.getDescription(), item.getId(), item.isDone()
        );
    }
}
