package ru.job4j;

import ru.job4j.carsplace.models.Add;
import ru.job4j.carsplace.models.Car;
import ru.job4j.carsplace.models.KeyValue;
import ru.job4j.todolist.models.Item;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import static java.lang.String.format;

/**
 * JSONBuilder
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class JSONBuilder {
    /**
     * Build JSON from Item.
     *
     * @param item pars object
     * @return JSON string
     */
    public static String build(final Item item) {
        return format(
                "{\"desc\": \"%s\", \"id\": %d, \"done\": %b}",
                item.getDescription(), item.getId(), item.isDone()
        );
    }

    /**
     * Build JSON from Item.
     *
     * @param add pars object
     * @return JSON string
     */
    @SuppressWarnings("SuspiciousDateFormat")
    public static String build(final Add add) {
        Car car = add.getCar();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String create = add.getCreateTime().toLocalDateTime().format(formatter);
        StringJoiner photos = new StringJoiner(", ", "[", "]");
        add.getAllPhotos().forEach(
                photo -> photos.add(format("{\"id\": %s, \"name\": \"%s\"}", photo.getId(), photo.getName()))
                );
        String frame = new StringJoiner(", ", "{", "}")
                .add("\"id\": %s, \"name\": \"%s\", \"desc\": \"%s\", \"price\": %s, \"odd\": %s, \"placed\": %s")
                .add("\"user\": %s, \"car\": {\"id\": %s, \"maker\": \"%s\", \"model\": \"%s\", \"gen\": \"%s\"")
                .add("\"body\": \"%s\", \"doors\": %s, \"engine\": \"%s\", \"drive\": \"%s\", \"gearBox\": \"%s\"}")
                .add("\"create\": \"%s\"")
                .add("\"year\": \"%s\"")
                .add("\"photos\": %s").toString();
        return format(
                frame, add.getId(), add.getName(), add.getDescription(), add.getPrice(), add.getOdd(), add.isPlaced(),
                add.getAddOwner().getId(), car.getId(), car.getMaker().getName(), car.getModel().getName(),
                car.getGeneration().getName(), car.getBody().getName(), car.getDoors().getName(),
                car.getEngine().getName(), car.getDrive().getName(), car.getGearBox().getName(), create, add.getYear(),
                photos
        );
    }

    /**
     * Build string.
     *
     * @param keyValue the kay value
     * @return the string
     */
    public static String build(final KeyValue keyValue) {
        return format("{\"id\": %s, \"name\": \"%s\"}",
                keyValue.getId(), keyValue.getName());
    }
}
