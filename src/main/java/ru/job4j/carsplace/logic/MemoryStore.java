package ru.job4j.carsplace.logic;

import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MemoryStore
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class MemoryStore implements StoreCarDesc {
    private final static MemoryStore STORE = new MemoryStore();
    private final List<Add> addList = new CopyOnWriteArrayList<>();
    private final List<Photo> photoList = new CopyOnWriteArrayList<>();
    private final List<KeyValue> models = new CopyOnWriteArrayList<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return STORE;
    }

    public void addModels(final List<KeyValue> models) {
        this.models.addAll(models);
    }

    @Override
    public List<KeyValue> getAllMakers() {
        return this.models;
    }

    @Override
    public List<KeyValue> getModelsOfMaker(Maker maker) {
        return null;
    }

    @Override
    public List<Generation> getGensOfModel(Model model) {
        return null;
    }

    @Override
    public List<KeyValue> getBodyByGen(Generation generation) {
        return null;
    }

    @Override
    public List<KeyValue> getEnginesByBody(Body setId) {
        return null;
    }

    @Override
    public List<KeyValue> getDoorsByBody(Body setId) {
        return null;
    }

    @Override
    public List<KeyValue> getGearBoxByEngine(Engine setId) {
        return null;
    }

    @Override
    public List<KeyValue> getDriveByEngine(Engine setId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void addOrUpdateAdd(Add add) {
        this.addList.add(add);
        add.setId(this.addList.indexOf(add));
    }

    @Override
    public void addPhotos(List<Photo> photos) {
        if (photos.size() > 0) {
            this.photoList.addAll(photos);
            photos.forEach(photo -> photo.setId(this.photoList.indexOf(photo)));
            photos.get(0).getAdd().setAllPhotos(Set.of(photos.get(0)));
        }
    }

    @Override
    public List<Add> allAdd() {
        return this.addList;
    }

    @Override
    public Car add(Car element) {
        return null;
    }

    @Override
    public void update(Car element) {
    }

    @Override
    public void delete(Car element) {
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Car element) {
        return null;
    }
}
