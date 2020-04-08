package ru.job4j.hello.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * User
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 08.04.2020
 */
public class User {
    private int id;
    private String name;
    private Calendar expired;

    public int getId() {
        return this.id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Timestamp getExpired() {
        return expired == null ? null : new Timestamp(this.expired.getTimeInMillis());
    }

    public Calendar getCalendar() {
        return this.expired;
    }

    public User setExpired(Timestamp time) {
        if (time != null) {
            this.expired = new GregorianCalendar();
            this.expired.setTimeInMillis(time.getTime());
        }
        return this;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", expired=" + (expired == null ? null : expired.getTime())
                + '}';
    }
}
