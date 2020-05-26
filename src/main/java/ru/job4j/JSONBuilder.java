package ru.job4j;

import org.json.JSONStringer;
import ru.job4j.carsplace.models.Add;
import ru.job4j.carsplace.models.Car;
import ru.job4j.todolist.models.Item;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public static String build(final List<Add> add) {
        JSONStringer result = new JSONStringer();
        result.array();
        add.forEach(item -> {
                    Car car = item.getCar();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
                    String create = item.getCreateTime().toLocalDateTime().format(formatter);
                    result.object()
                            .key("id").value(item.getId())
                            .key("name").value(item.getName())
                            .key("desc").value(item.getDescription())
                            .key("price").value(item.getPrice())
                            .key("odd").value(item.getOdd())
                            .key("placed").value(item.isPlaced())
                            .key("user").value(item.getAddOwner().getId())
                            .key("create").value(create)
                            .key("year").value(item.getYear())
                            .key("car")
                            .object()
                            .key("id").value(car.getId())
                            .key("maker").value(car.getMaker().getName())
                            .key("model").value(car.getModel().getName())
                            .key("gen").value(car.getGeneration().getName())
                            .key("body").value(car.getBody().getName())
                            .key("doors").value(car.getDoors().getName())
                            .key("engine").value(car.getEngine().getName())
                            .key("drive").value(car.getDrive().getName())
                            .key("gearBox").value(car.getGearBox().getName())
                            .endObject()
                            .key("photos")
                            .array();
                    item.getAllPhotos().forEach(p -> result
                            .object()
                            .key("id").value(p.getId())
                            .key("name").value(p.getName())
                            .endObject()
                    );
                    result.endArray().endObject();
                });
        result.endArray();
        return result.toString();
    }
}
