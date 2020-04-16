package ru.job4j.todolist.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
    private static final SessionFactory FACTORY =  new Configuration().configure().buildSessionFactory();

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
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
        return item;
    }

    /**
     * Update Item.
     * @param item new data to item
     * @return updated Item.
     */
    @Override
    public Item update(final Item item) {
        return this.add(item);
    }

    /**
     * Delete Item.
     * @param item item to delete
     * @return deleted Item.
     */
    @Override
    public Item delete(final Item item) {
        Item result = this.findById(item);
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
        return result;
    }

    /**
     * All Item finder order by create time or {@code null} if not items.
     * @return all items from memory or {@code null} if not items
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new LinkedList<>();
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            List list = session.createQuery("from Item order by create asc").list();
            session.getTransaction().commit();
            for (Object o : list) {
                if (o != null) {
                    result.add((Item) o);
                }
            }
        }
        return result.size() == 0 ? null : result;
    }

    /**
     * Find Item by id.
     * @param item item to find
     * @return founded Item on {@code null}
     */
    @Override
    public Item findById(final Item item) {
        Item result;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            result = session.find(Item.class, item.getId());
            session.getTransaction().commit();
        }
        return result;
    }
}
