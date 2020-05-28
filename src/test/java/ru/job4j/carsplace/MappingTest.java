package ru.job4j.carsplace;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carsplace.logic.DBStore;
import ru.job4j.carsplace.logic.StoreCarDesc;
import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
/**
 * MappingTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class MappingTest {
    private static final String DIESEL = "1.5d 116 л.c. дизель";
    private static final String ROSSY = "Rossy";
    private static final String MC_RAE = "McRae";
    private static final String GOLF = "Golf";
    private static final String DEF_PHOTO = "default.png";
    private SessionFactory factory;
    private static final StoreCarDesc STORE = DBStore.getInstance();

    @Before
    public void setUp() {
        factory = new Configuration().configure().buildSessionFactory();
    }
    @After
    public void closeUp() {
        factory.close();
    }
    @Test
    public void whenAddCarThenCarHawEngine() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Engine engine = new Engine().setName(DIESEL);
            session.saveOrUpdate(engine);
            Car car = new Car()
                    .setName("Saab")
                    .setEngine(new Engine().setId(engine.getId()));
            session.save(car);
            int carId = car.getId();
            assertThat(carId, not(is(0)));
            session.evict(car);
            Car getCar = session.get(Car.class, carId);
            assertThat(getCar.getEngine().getName(), is(DIESEL));
            session.getTransaction().commit();
        }
    }

    @Test
    public void whenDriverAddTheDriverInStore() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Driver driver = new Driver().setName(ROSSY);
            session.saveOrUpdate(driver);
            int driverId = driver.getId();
            assertThat(driverId, not(is(0)));
            session.evict(driver);
            Driver driverGet = session.get(Driver.class, driverId);
            assertThat(driverGet.getName(), is(ROSSY));
            assertThat(driverGet.getId(), is(driverId));
            session.getTransaction().commit();
        }
    }

    @Test
    public void whenCarHaveOwnersThenReturnDrivers() {
        int golfId;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Driver rossy = new Driver().setName(ROSSY);
            Driver mcRae = new Driver().setName(MC_RAE);
            Car golf = new Car().setName(GOLF);
            Engine engine = new Engine().setId(1);
            Set<Driver> drivers = new HashSet<>();
            drivers.add(mcRae);
            drivers.add(rossy);
            session.saveOrUpdate(rossy);
            session.saveOrUpdate(mcRae);
            session.saveOrUpdate(new Engine().setName(DIESEL));
            golf.setDrivers(drivers).setEngine(engine);
            session.saveOrUpdate(golf);
            golfId = golf.getId();
            assertThat(golfId, not(is(0)));
            session.getTransaction().commit();
        }
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Car car = session.get(Car.class, golfId);
            assertThat(
                    car.getDrivers().stream().map(Driver::getName).collect(Collectors.toSet()),
                    containsInAnyOrder(ROSSY, MC_RAE)
            );
            session.getTransaction().commit();
        }
    }


    private Add newAdd(String makerName) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Maker maker = new Maker().setName(makerName);
            Model model = new Model().setName("model");
            Generation generation = new Generation().setName("generation");
            BodyName bodyName = new BodyName().setName("bodyName");
            Body body = new Body().setBodyName(bodyName);
            Doors doors = new Doors().setName("doors");
            Engine engine = new Engine().setName("engine");
            Drive drive = new Drive().setName("drive");
            GearBox gearBox = new GearBox().setName("gearBox");
            User addOwner = new User().setName("addOwner");
            session.save(maker);
            session.save(model.setMaker(maker));
            session.save(generation.setModel(model));
            session.save(bodyName);
            session.save(body.setGeneration(generation));
            session.save(doors.setBodyGenId(body));
            session.save(gearBox);
            session.save(drive);
            session.save(engine.setBody(body));
            session.save(addOwner);
            Car car = new Car()
                    .setMaker(maker)
                    .setModel(model)
                    .setGeneration(generation)
                    .setBody(body)
                    .setDoors(doors)
                    .setEngine(engine)
                    .setDrive(drive)
                    .setGearBox(gearBox);
            Add add = new Add()
                    .setCar(car)
                    .setName("2")
                    .setDescription("222")
                    .setOdd(21421)
                    .setPrice(1241234)
                    .setAddOwner(addOwner);
            session.getTransaction().commit();
            return add;
        }
    }

    @Test
    public void whenAddAddThenAddAdd() {
        STORE.addOrUpdateAdd(this.newAdd("name"));
        List<Add> adds = STORE.allAdd();
        assertThat(adds.size(), is(1));
        Add add = adds.iterator().next();
        assertThat(add.getId(), is(1));
        assertThat(add.getCar().getId(), is(1));
    }

    @Test
    public void whenGetAddOfMakerThenReturn2Add() {
        Add firstAdd = this.newAdd("First");
        Add second = this.newAdd("Second");
        STORE.addOrUpdateAdd(firstAdd);
        STORE.addOrUpdateAdd(firstAdd.setId(0));
        STORE.addOrUpdateAdd(second);
        assertThat(STORE.getAddOfMaker(new Maker().setId(0)).size(), is(3));
        assertThat(STORE.getAddOfMaker(new Maker().setId(1)).size(), is(2));
        assertThat(STORE.getAddOfMaker(new Maker().setId(2)).size(), is(1));
    }

    @Test
    public void whenGetMakersOfAddThenReturnIchMakerOnes() {
        Add firstAdd = this.newAdd("First");
        Add second = this.newAdd("Second");
        STORE.addOrUpdateAdd(firstAdd);
        STORE.addOrUpdateAdd(firstAdd.setId(0));
        STORE.addOrUpdateAdd(second);
        STORE.addOrUpdateAdd(second.setId(0));
        STORE.addOrUpdateAdd(second.setId(0));
        STORE.addOrUpdateAdd(second.setId(0));
        assertThat(STORE.getAddOfMaker(new Maker().setId(0)).size(), is(6));
        List<KeyValue> makers = STORE.getMakersOfAdd();
        assertThat(makers.size(), is(2));
        makers.forEach(keyValue ->
                assertThat(keyValue.getName(), anyOf(is("First"), is("Second")))
        );
    }

    @Test
    public void whenGetAddAfterDateThen1() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Add firstAdd = this.newAdd("First");
        Add second = this.newAdd("Second").setCreateTime(new Timestamp(calendar.getTimeInMillis()));
        STORE.addOrUpdateAdd(firstAdd);
        STORE.addOrUpdateAdd(second);
        assertThat(STORE.getAddAfterDate(new Timestamp(calendar.getTimeInMillis())).size(), is(1));
    }

    @Test
    public void whenGetAddHasPhotosThenPhotosNameNotDefault() {
        Add firstAdd = this.newAdd("First");
        Add secondAdd = this.newAdd("Second");
        STORE.addOrUpdateAdd(firstAdd);
        STORE.addOrUpdateAdd(firstAdd.setId(0));
        STORE.addOrUpdateAdd(secondAdd);
        STORE.addPhotos(
                List.of(
                        new Photo().setName(DEF_PHOTO).setAdd(firstAdd),
                        new Photo().setName("dif").setAdd(secondAdd),
                        new Photo().setName("DIFDIF").setAdd(secondAdd)
                )
        );
        List<Add> adds = STORE.getAddHasPhoto();
        assertThat(adds.size(), is(1));
        assertThat(adds.iterator().next().getCar().getMaker().getName(), is("Second"));
    }

    @Test
    public void whenGetAllMakersThe2() {
        this.newAdd("First");
        this.newAdd("Second");
        assertThat(STORE.getAllMakers().size(), is(2));
    }

    @Test
    public void whenGetModelsOfMakerThe2() {
        this.newAdd("First");
        Maker maker = this.newAdd("Second").getCar().getMaker();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new Model().setMaker(maker).setName("2"));
            session.getTransaction().commit();
        }
        assertThat(STORE.getModelsOfMaker(maker).size(), is(2));
    }

    @Test
    public void whenGetGenOfModelsThe2() {
        this.newAdd("First");
        this.newAdd("First2");
        Car car = this.newAdd("Second").getCar();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new Generation().setModel(car.getModel()));
            session.getTransaction().commit();
        }
        assertThat(STORE.getGensOfModel(car.getModel()).size(), is(2));
    }

    @Test
    public void whenGetBodyByGenThe2() {
        this.newAdd("First");
        this.newAdd("First2");
        Car car = this.newAdd("Second").getCar();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new Body()
                    .setGeneration(car.getGeneration())
                    .setBodyName(new BodyName().setId(1))
            );
            session.getTransaction().commit();
        }
        assertThat(STORE.getBodyByGen(car.getGeneration()).size(), is(2));
    }

    @Test
    public void whenGetEngineByBodyThe2() {
        this.newAdd("First");
        this.newAdd("First2");
        Car car = this.newAdd("Second").getCar();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new Engine()
                    .setBody(car.getBody())
                    .setDrive(car.getDrive())
            );
            session.getTransaction().commit();
        }
        assertThat(STORE.getEnginesByBody(car.getBody()).size(), is(2));
    }

    @Test
    public void whenGetDoorsByBodyThe2() {
        this.newAdd("First");
        this.newAdd("First2");
        Car car = this.newAdd("Second").getCar();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(new Doors().setBodyGenId(car.getBody()));
            session.getTransaction().commit();
        }
        assertThat(STORE.getDoorsByBody(car.getBody()).size(), is(2));
    }
}
