package ru.job4j.hello.models;

import ru.job4j.carsplace.models.Add;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.StringJoiner;

/**
 * User
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 08.04.2020
 */
public class User {
    private int id;
    private String name;
    private Calendar expired;
    private String login;
    private String password;
    private Set<Add> allAdd;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public User setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public User setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets expired.
     *
     * @return the expired
     */
    public Timestamp getExpired() {
        return expired == null ? null : new Timestamp(this.expired.getTimeInMillis());
    }

    /**
     * Gets calendar.
     *
     * @return the calendar
     */
    @SuppressWarnings("unused")
    public Calendar getCalendar() {
        return this.expired;
    }

    /**
     * Sets expired.
     *
     * @param time the time
     * @return the expired
     */
    public User setExpired(Timestamp time) {
        if (time != null) {
            this.expired = new GregorianCalendar();
            this.expired.setTimeInMillis(time.getTime());
        }
        return this;
    }

    /**
     * Sets expired.
     *
     * @param expired the expired
     * @return the expired
     */
    public User setExpired(Calendar expired) {
        this.expired = expired;
        return this;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     * @return the login
     */
    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     * @return the password
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Gets all add.
     *
     * @return the all add
     */
    public Set<Add> getAllAdd() {
        return this.allAdd;
    }

    /**
     * Sets all add.
     *
     * @param allAdd the all add
     * @return the all add
     */
    public User setAllAdd(Set<Add> allAdd) {
        this.allAdd = allAdd;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("expired=" + expired)
                .add("login='" + login + "'")
                .toString();
    }
}
