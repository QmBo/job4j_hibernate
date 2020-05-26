package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Function;

/**
 * DBConnect
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class DBConnect {
    private static final SessionFactory FACTORY =  new Configuration().configure().buildSessionFactory();
    private static final DBConnect CONNECT = new DBConnect();

    /**
     * Instantiates a new Db connect.
     */
    private DBConnect() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DBConnect getInstance() {
        return CONNECT;
    }

    /**
     * Transaction.
     *
     * @param <T>     result
     * @param command command
     * @return transaction of command
     */
    public <T> T tx(final Function<Session, T> command) {
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
