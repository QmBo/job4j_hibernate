package ru.job4j.hello;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.hello.models.User;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
/**
 * HibernateRun
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 08.04.2020
 */
public class HibernateRun {
    /**
     * Start point.
     * @param args not
     */
    public static void main(String[] args) {
        new HibernateRun().go();
    }

    /**
     * Main work.
     */
    private void go() {
        try (
                SessionFactory factory = new Configuration()
                        .configure()
                        .buildSessionFactory()
        ) {
            this.find(factory);
            User user = new User().setName("New user");
            this.printUser("Before create:", Collections.singletonList(user));
            this.createOrUpdate(factory, user);
            this.printUser("After create:", Collections.singletonList(user));

            User find = new User().setId(user.getId());
            this.printUser("Find User: ", Collections.singletonList(find));
            this.find(factory, find);

            user = new User().setId(find.getId()).setName("!!!!!!!!!!!!!").setExpired(new Timestamp(1L));
            this.printUser("Before update:", Collections.singletonList(user));
            this.createOrUpdate(factory, user);

            this.find(factory, new User().setId(user.getId()));
            this.delete(factory, new User().setId(user.getId()));
            this.find(factory);
        }
    }

    /**
     * Delete User from users table.
     * @param factory SessionFactory
     * @param user user with id to delete
     */
    private void delete(SessionFactory factory, User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Find all Users.
     * @param factory SessionFactory.
     */
    private void find(SessionFactory factory) {
        this.find(factory, null);
    }

    /**
     * Find user by id.
     * @param factory SessionFactory
     * @param user user with id
     */
    private void find(SessionFactory factory, User user) {
        try (Session session = factory.openSession()) {
            if (user != null) {
                User result = session.find(User.class, user.getId());
                this.printUser("Find in DB:", Collections.singletonList(result));
            } else {
                List result = session.createQuery("from User").list();
                if (result.size() == 0) {
                    System.out.println("No entry found in DB!");
                }
                for (Object o : result) {
                    System.out.printf("Find in DB: %s\n", (User) o);
                }
            }
        }
    }

    /**
     * Create or update user
     * @param factory SessionFactory
     * @param user user
     */
    private void createOrUpdate(SessionFactory factory, User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Message printer.
     * @param message message
     * @param user user to print.
     */
    private void printUser(String message, List<User> user) {
        System.out.printf("%s %s\n", message, user);
    }
}
