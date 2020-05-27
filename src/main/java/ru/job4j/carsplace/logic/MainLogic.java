package ru.job4j.carsplace.logic;

import ru.job4j.carsplace.models.*;
import ru.job4j.hello.models.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * MainLogic
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class MainLogic {
    private final static MainLogic LOGIC = new MainLogic();
    private static final String MAKER = "maker";
    private static final String GEN = "gen";
    private static final String MODEL = "model";
    private static final String BODY = "body";
    private static final String ENGINE = "engine";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String DOORS = "doors";
    private static final String DRIVE = "drive";
    private static final String GEAR_BOX = "gearBox";
    private static final String NAME = "name";
    private static final String ODD = "odd";
    private static final String PRICE = "price";
    private static final String DESCRIPTION = "description";
    private static final String NUMBERS = "numbersOfPhoto";
    private static final String PHOTO_ID = "photoId";
    private static final String YEAR = "year";
    private static final String REGEX = "[^0-9]";
    private static final String EMPTY = "";
    private static final String ID = "id";
    private static final String PLACED = "placed";
    private final StoreCarDesc store = DBStore.getInstance();

    /**
     * Instantiates a new Main logic.
     */
    private MainLogic() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MainLogic getInstance() {
        return LOGIC;
    }


    /**
     * Gets all makers.
     *
     * @return the all makers
     */
    public List<KeyValue> getAllMakers() {
        return this.store.getAllMakers();
    }

    /**
     * Gets all models.
     *
     * @param req the req
     * @return the all models
     */
    public List<KeyValue> getAllModels(final HttpServletRequest req) {
        return this.get(id -> this.store.getModelsOfMaker(new Maker().setId(id)),
                MAKER, req);
    }

    /**
     * Gets years of model.
     *
     * @param req the req
     * @return the years of model
     */
    public List<KeyValue> getYearsOfModel(final HttpServletRequest req) {
        return this.getYears(
                this.get(id -> this.store.getGensOfModel(new Model().setId(id)),
                        MODEL, req)
        );
    }

    /**
     * Gets all generations.
     *
     * @param req the req
     * @return the all generations
     */
    public List<KeyValue> getAllGenerations(final HttpServletRequest req) {
        List<KeyValue> result = null;
        List<Generation> gens = this.get(
                id -> this.store.getGensOfModel(new Model().setId(id)),
                MODEL, req);
        String parameter = req.getParameter(YEAR);
        if (!EMPTY.equals(parameter) && !parameter.matches(REGEX)) {
            int year = Integer.parseInt(parameter);
            result = gens.stream()
                    .filter(gen -> (gen.getStartYear() <= year && gen.getEndYear() >= year))
                    .collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Gets all body.
     *
     * @param req the req
     * @return the all body
     */
    public List<KeyValue> getAllBody(final HttpServletRequest req) {
        return this.get(id -> this.store.getBodyByGen(new Generation().setId(id)),
                GEN, req);
    }

    /**
     * Gets all engines.
     *
     * @param req the req
     * @return the all engines
     */
    public List<KeyValue> getAllEngines(final HttpServletRequest req) {
        return this.get(id -> this.store.getEnginesByBody(new Body().setId(id)),
                BODY, req);
    }

    /**
     * Gets all doors.
     *
     * @param req the req
     * @return the all doors
     */
    public List<KeyValue> getAllDoors(final HttpServletRequest req) {
        return this.get(id -> this.store.getDoorsByBody(new Body().setId(id)),
                BODY, req);
    }

    /**
     * Gets all gear box.
     *
     * @param req the req
     * @return the all gear box
     */
    public List<KeyValue> getAllGearBox(final HttpServletRequest req) {
        return this.get(id -> this.store.getGearBoxByEngine(new Engine().setId(id)),
                ENGINE, req);
    }

    /**
     * Gets all drive.
     *
     * @param req the req
     * @return the all drive
     */
    public List<KeyValue> getAllDrive(final HttpServletRequest req) {
        return this.get(id -> this.store.getDriveByEngine(new Engine().setId(id)),
                ENGINE, req);
    }

    /**
     * Get t.
     *
     * @param <T>     the type parameter
     * @param command the command
     * @param kay     the kay
     * @param req     the req
     * @return the t
     */
    private  <T> T get(final Function<Integer, T> command, String kay, HttpServletRequest req) {
        T result = null;
        String id = req.getParameter(kay);
        if (!EMPTY.equals(id) && !id.matches(REGEX)) {
            result = command.apply(Integer.valueOf(id));
        }
        return result;
    }

    /**
     * Gets years.
     *
     * @param gens the gens
     * @return the years
     */
    private List<KeyValue> getYears(final List<Generation> gens) {
        Set<Integer> years = new TreeSet<>(Comparator.reverseOrder());
        gens.forEach(gen -> {
            int startYear = gen.getStartYear();
            int endYear = gen.getEndYear();
            for (int i = startYear; i <= endYear; i++) {
                years.add(i);
            }
        });
        List<KeyValue> result = new LinkedList<>();
        years.forEach(integer -> result.add(new Year(integer, integer.toString())));
        return result;
    }

    /**
     * Is credentials.
     *
     * @param req request
     * @return answer boolean
     */
    public boolean isCredentials(final HttpServletRequest req) {
        boolean result = false;
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        if (login != null && password != null) {
            List<User> users = this.store.getAllUsers();
            for (User user : users) {
                if (req.getParameter(LOGIN).equals(user.getLogin())) {
                    result = req.getParameter(PASSWORD).equals(user.getPassword());
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Add add.
     *
     * @param req the req
     */
    public void addAdd(final HttpServletRequest req) {
        Car car = new Car()
                .setMaker(new Maker().setId(Integer.parseInt((String) req.getAttribute(MAKER))))
                .setModel(new Model().setId(Integer.parseInt((String) req.getAttribute(MODEL))))
                .setGeneration(new Generation().setId(Integer.parseInt((String) req.getAttribute(GEN))))
                .setBody(new Body().setId(Integer.parseInt((String) req.getAttribute(BODY))))
                .setDoors(new Doors().setId(Integer.parseInt((String) req.getAttribute(DOORS))))
                .setEngine(new Engine().setId(Integer.parseInt((String) req.getAttribute(ENGINE))))
                .setDrive(new Drive().setId(Integer.parseInt((String) req.getAttribute(DRIVE))))
                .setGearBox(new GearBox().setId(Integer.parseInt((String) req.getAttribute(GEAR_BOX))));
        Add add = new Add()
                .setCar(car)
                .setPlaced(true)
                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                .setName((String) req.getAttribute(NAME))
                .setYear(Integer.parseInt((String) req.getAttribute(YEAR)))
                .setDescription((String) req.getAttribute(DESCRIPTION))
                .setOdd(Long.parseLong((String) req.getAttribute(ODD)))
                .setPrice(Long.parseLong((String) req.getAttribute(PRICE)))
                .setAddOwner(new User().setId(Integer.parseInt((String) req.getAttribute(LOGIN))));
        int number = (Integer) req.getAttribute(NUMBERS);
        this.store.addOrUpdateAdd(add);
        List<Photo> photos = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            String name = format("%s%s", PHOTO_ID, i);
            photos.add(new Photo()
                    .setName((String) req.getAttribute(name))
                    .setAdd(add));
        }
        this.store.addPhotos(photos);
    }

    /**
     * Gets user by login.
     *
     * @param req the req
     * @return the user by login
     */
    public User getUserByLogin(final HttpServletRequest req) {
        List<User> allUsers = this.store.getAllUsers();
        String login = req.getParameter(LOGIN);
        return allUsers.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }

    /**
     * Gets all add.
     *
     * @return the all add
     */
    public List<Add> getAllAdd() {
        return this.store.allAdd();
    }

    /**
     * Change status.
     *
     * @param req the req
     */
    public void changeStatus(HttpServletRequest req) {
        String id = req.getParameter(ID);
        String placed = req.getParameter(PLACED);
        if (!id.isEmpty() && !id.matches(REGEX) && placed.matches("(true|false)")) {
            this.getAllAdd()
                    .stream()
                    .filter(o -> o.getId() == Integer.parseInt(id))
                    .findFirst().ifPresent(add -> {
                add.setPlaced(Boolean.parseBoolean(placed));
                this.store.addOrUpdateAdd(add);
            });
        }
    }

    /**
     * Gets makers of add.
     *
     * @return the makers of add
     */
    public List<KeyValue> getMakersOfAdd() {
        return this.store.getMakersOfAdd();
    }

    /**
     * Add of maker list.
     *
     * @param req the req
     * @return the list
     */
    public List<Add> addOfMaker(final HttpServletRequest req) {
       return this.get(id -> this.store.addOfMaker(new Maker().setId(id)),
               MAKER, req);
    }

    /**
     * Gets add has photo.
     *
     * @return the add has photo
     */
    public List<Add> getAddHasPhoto() {
        return this.store.getAddHasPhoto();
    }

    /**
     * Gets add of last day.
     *
     * @return the add of last day
     */
    public List<Add> getAddOfLastDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.HOUR, -24);
        return this.store.getAddAfterDate(new Timestamp(calendar.getTimeInMillis()));
    }
}
