package ru.job4j.todolist.logic;

import ru.job4j.DBConnect;
import ru.job4j.Store;
import ru.job4j.todolist.models.Item;
import java.util.*;


/**
 * DBStore
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class DBStore implements Store<Item> {
    private static final Store<Item> INSTANCE = new DBStore();
    private final DBConnect connect = DBConnect.getInstance();

    /**
     * Private constructor.
     */
    private DBStore() {
    }

    /**
     * Get instance.
     * @return instance
     */
    public static Store<Item> getInstance() {
        return INSTANCE;
    }

    /**
     * Add new Item and set Item id.
     * @param item Item to add
     * @return Item with id
     */
    @Override
    public Item add(final Item item) {
        return this.connect.tx(session -> {
            session.saveOrUpdate(item);
            return item;
        });
    }

    /**
     * Update Item.
     * @param item new data to item
     */
    @Override
    public void update(final Item item) {
        this.add(item);
    }

    /**
     * Delete Item.
     * @param item item to delete
     */
    @Override
    public void delete(final Item item) {
        this.connect.tx(session -> {
            Item result = this.findById(item);
            session.delete(item);
            return result;
        });
    }

    /**
     * All Item finder order by create time or {@code null} if not items.
     * @return all items from memory or {@code null} if not items
     */
    @Override
    public List<Item> findAll() {
        return this.connect.tx(session -> {
            List<Item> result  = session.createQuery("from Item order by create asc", Item.class).list();
            return result.size() == 0 ? null : result;
        });
    }

    /**
     * Find Item by id.
     * @param item item to find
     * @return founded Item on {@code null}
     */
    @Override
    public Item findById(final Item item) {
        return this.connect.tx(session -> session.find(Item.class, item.getId()));
    }
}
