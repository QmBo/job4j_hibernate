package ru.job4j.carsplace;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
            Car car = new Car()
                    .setName("Saab")
                    .setEngine(new Engine().setId(2));
            session.save(car);
            int carId = car.getId();
            assertThat(carId, not(is(0)));
            session.evict(car);
            Car getCar = session.get(Car.class, carId);
            assertThat(getCar.getEngine().getName(), is(DIESEL));
            session.evict(getCar);
            session.delete(new Car().setId(carId));
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
            assertThat(driver.getId(), is(driverId));
            session.delete(driverGet);
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
            Engine engine = new Engine().setId(2);
            Set<Driver> drivers = new HashSet<>();
            drivers.add(mcRae);
            drivers.add(rossy);
            session.saveOrUpdate(rossy);
            session.saveOrUpdate(mcRae);
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
            Set<Driver> drivers = car.getDrivers();
            System.out.println(car);
            session.delete(car);
            for (Driver driver : drivers) {
                session.delete(driver);
            }
            session.getTransaction().commit();
        }
    }

    @Test
    public void whenGetModelTheModelHaveMaker() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Object[] makersName = session.createQuery("from Maker", Maker.class).list()
                    .stream()
                    .map(Maker::getName)
                    .toArray();
            List<Model> model = session.createQuery("from Model", Model.class).list();
            session.getTransaction().commit();
            for (Model car : model) {
                System.out.println(car);
                assertThat(car.getMaker().getName(), isOneOf(makersName));
            }
        }
    }

    @Test
    public void whenAddAddThenAddAdd() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Car car = new Car()
                    .setMaker(new Maker().setId(1))
                    .setModel(new Model().setId(2))
                    .setGeneration(new Generation().setId(2))
                    .setBody(new Body().setId(22))
                    .setDoors(new Doors().setId(2))
                    .setEngine(new Engine().setId(2))
                    .setDrive(new Drive().setId(2))
                    .setGearBox(new GearBox().setId(2));
            Add add = new Add()
                    .setCar(car)
                    .setName("2")
                    .setDescription("222")
                    .setOdd(21421)
                    .setPrice(1241234)
                    .setAddOwner(new User().setId(1));
            session.saveOrUpdate(add);
            session.saveOrUpdate(car);
            assertThat(car.getId(), not(is(0)));
            assertThat(add.getId(), not(is(0)));
            session.delete(add);
            session.delete(car);
            session.getTransaction().commit();
        }
    }


    @Test
    public void whenGetGenerationsTheItHaveModelAndProductionYears() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Object[] modelsNames = session.createQuery("from Model ", Model.class).list()
                    .stream()
                    .map(Model::getName)
                    .toArray();
            List<Generation> gen = session.createQuery("from Generation", Generation.class).list();
            session.getTransaction().commit();
            for (Generation generation : gen) {
                System.out.println(generation);
                assertThat(generation.getModel().getName(), isOneOf(modelsNames));
            }
        }
    }

    @Test
    public void whenGetMakersFromAddGroupByMakerThenIchMakerOnes() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            List<KeyValue> adds = session.createQuery(
                    "from Maker as m where m.id in (select a.car.maker.id from Add as a group by a.car.maker.id)",
                    KeyValue.class
            ).list();
            session.getTransaction().commit();
            final int size = adds.size();
            Set<String> names = new TreeSet<>(String::compareTo);
            adds.forEach(keyValue -> names.add(keyValue.getName()));
            assertThat(size, is(names.size()));
        }
    }

    @SuppressWarnings("JpaQlInspection")
    @Test
    public void whenGetAddHasPhotosThenPhotosNameNotDefault() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            List<Add> adds =  session.createQuery(
                    "from Add a where a.id in(select p.add.id from Photo p where p.name !=:defaultName) group by a",
                    Add.class)
                    .setParameter("defaultName", DEF_PHOTO)
                    .list();
            session.getTransaction().commit();
            System.out.println(adds);
            final int size = adds.size();
            Set<Integer> names = new TreeSet<>(Integer::compareTo);
            adds.forEach(keyValue -> names.add(keyValue.getId()));
            assertThat(size, is(names.size()));
            adds.forEach(add ->
                    add.getAllPhotos().forEach(photo ->
                            assertThat(photo.getName(), is(not(DEF_PHOTO)))
                    )
            );
        }
    }
}