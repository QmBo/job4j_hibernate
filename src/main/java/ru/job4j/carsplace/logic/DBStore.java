package ru.job4j.carsplace.logic;

import ru.job4j.DBConnect;
import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * DBStore
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class DBStore implements StoreCarDesc {
    private static final String DEF_PHOTO = "default.png";
    private static final StoreCarDesc STORE = new DBStore();
    private final DBConnect connect = DBConnect.getInstance();

    /**
     * Private constructor.
     */
    private DBStore() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static StoreCarDesc getInstance() {
        return STORE;
    }

    @Override
    public Car add(final Car element) {
        return this.connect.tx(session -> {
            session.saveOrUpdate(element);
            return element;
        });
    }

    @Override
    public List<KeyValue> getAllMakers() {
        return this.connect.tx(session -> session.createQuery("from Maker", KeyValue.class).list());
    }

    @Override
    public List<KeyValue> getModelsOfMaker(final Maker setId) {
        return this.connect.tx(
                session -> new  LinkedList<>(
                        session.load(Maker.class, setId.getId()).getAllModels()
                )
        );
    }

    @Override
    public List<Generation> getGensOfModel(Model setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Model.class, setId.getId()).getGenerations()
                )
        );
    }

    @Override
    public List<KeyValue> getBodyByGen(final Generation setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Generation.class, setId.getId()).getAllBody()
                )
        );
    }

    @Override
    public List<KeyValue> getEnginesByBody(final Body setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Body.class, setId.getId()).getAllEngines()
                )
        );
    }

    @Override
    public List<KeyValue> getDoorsByBody(final Body setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Body.class, setId.getId()).getAllDoors()
                )
        );
    }

    @Override
    public List<KeyValue> getGearBoxByEngine(final Engine setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Engine.class, setId.getId()).getAllGearBox()
                )
        );
    }

    @Override
    public List<KeyValue> getDriveByEngine(final Engine setId) {
        return this.connect.tx(
                session -> new LinkedList<>(
                        session.load(Engine.class, setId.getId()).getAllDrives()
                )
        );
    }

    @Override
    public List<User> getAllUsers() {
        return this.connect.tx(session ->
                session.createQuery("from User", User.class).list());
    }

    @Override
    public void addOrUpdateAdd(final Add add) {
        this.connect.tx(session -> {
            session.saveOrUpdate(add);
            return add;
        });
    }

    @Override
    public void addPhotos(final List<Photo> photos) {
        photos.forEach(
                photo -> this.connect.tx(session -> session.save(photo))
        );
    }

    @Override
    public List<Add> allAdd() {
        return this.connect.tx(session ->
                session.createQuery("from Add as a order by a.createTime desc", Add.class).list());
    }

    @Override
    public List<Add> getAddOfMaker(Maker maker) {
        List<Add> result;
        if (maker.getId() == 0) {
            result = this.allAdd();
        } else {
            result = this.connect.tx(session ->
                    session.createQuery("from Add as a where a.car.maker.id= :makerId", Add.class)
                            .setParameter("makerId", maker.getId())
                            .list()
            );
        }
        return result;
    }

    @Override
    public List<KeyValue> getMakersOfAdd() {
        return this.connect.tx(session ->
                session.createQuery(
                        "from Maker as m where m.id in(select a.car.maker.id from Add as a group by a.car.maker.id)",
                        KeyValue.class)
                        .list()
        );
    }

    @Override
    public List<Add> getAddHasPhoto() {
        return this.connect.tx(session ->
                session.createQuery(
                        "from Add a where a.id in(select p.add.id from Photo p where p.name !=:defaultName) group by a",
                        Add.class)
                        .setParameter("defaultName", DEF_PHOTO)
                        .list()
        );
    }

    @Override
    public List<Add> getAddAfterDate(final Timestamp date) {
        return this.connect.tx(session ->
                session.createQuery("from Add a where a.createTime > :time", Add.class)
                .setParameter("time", date)
                        .list()
        );
    }
}
