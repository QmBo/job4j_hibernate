package ru.job4j.todolist.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.models.Item;

import java.util.*;
import java.util.function.Function;

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
        return this.tx(session -> {
            session.saveOrUpdate(item);
            return item;
        });
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
        return this.tx(session -> {
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
        return this.tx(session -> {
            List<Item> result = new LinkedList<>();
            List list = session.createQuery("from Item order by create asc").list();
            for (Object o : list) {
                if (o != null) {
                    result.add((Item) o);
                }
            }
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
        return this.tx(session -> session.find(Item.class, item.getId()));
    }

    /**
     * Transaction.
     * @param command command
     * @param <T> result
     * @return transaction of command
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = FACTORY.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
