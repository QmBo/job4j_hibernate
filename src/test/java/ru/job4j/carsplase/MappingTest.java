package ru.job4j.carsplase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carsplase.models.Car;
import ru.job4j.carsplase.models.Driver;
import ru.job4j.carsplase.models.Engine;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MappingTest {
    private static final String DIESEL = "Diesel";
    private static final String ROSSY = "Rossy";
    private static final String MC_RAE = "McRae";
    private static final String GOLF = "Golf";
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
            Engine engine = new Engine().setName(DIESEL);
            session.beginTransaction();
            session.save(engine);
            int engineId = engine.getId();
            assertThat(engineId, not(is(0)));
            Car car = new Car()
                    .setName("Saab")
                    .setEngine(
                            new Engine().setId(engineId)
                    );
            session.save(car);
            int carId = car.getId();
            assertThat(carId, not(is(0)));
            session.evict(car);
            Car getCar = session.get(Car.class, carId);
            assertThat(getCar.getEngine().getName(), is(DIESEL));
            session.evict(getCar);
            session.evict(engine);
            session.delete(new Car().setId(carId));
            session.delete(new Engine().setId(engineId));
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
            Engine engine = new Engine().setName(DIESEL);
            Set<Driver> drivers = new HashSet<>();
            drivers.add(mcRae);
            drivers.add(rossy);
            session.saveOrUpdate(engine);
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
            Engine engine = car.getEngine();
            session.delete(car);
            session.delete(engine);
            for (Driver driver : drivers) {
                session.delete(driver);
            }
            session.getTransaction().commit();
        }
    }
}