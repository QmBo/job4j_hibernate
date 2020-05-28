package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONStringer;
import ru.job4j.carsplace.logic.MainLogic;
import ru.job4j.carsplace.models.KeyValue;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * CreateServlet
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class CreateServlet extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(CreateServlet.class);
    private static final String LOAD = "load";
    private static final String MAKER = "maker";
    private static final String GEN = "gen";
    private static final String MODEL = "model";
    private static final String FIRST = "first";
    private static final String BODY = "body";
    private static final String SECOND = "second";
    private static final String ENGINE = "engine";
    private static final String LOGIN = "login";
    private static final String NUMBERS = "numbersOfPhoto";
    private static final String PHOTO_ID_0 = "photoId0";
    private static final String DEF_PHOTO = "default.png";
    private static final String YEAR = "year";
    private static final String HAVING_MAKER = "havingMaker";
    private final MainLogic logic = MainLogic.getInstance();
    private final Map<String, Function<HttpServletRequest, String>> getDispatch = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("windows-1251");
        try {
            this.add(req);
            resp.sendRedirect(format("%s/carsplace", req.getContextPath()));
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(this.request(this.getDispatcherInit(), req));
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Add.
     *
     * @param req the req
     */
    private void add(HttpServletRequest req) {
        ImageUploader.upload(this, req);
        if ((Integer) req.getAttribute(NUMBERS) == 0) {
            req.setAttribute(NUMBERS, 1);
            req.setAttribute(PHOTO_ID_0, DEF_PHOTO);
        }
        this.logic.addAdd(req);
    }

    /**
     * Init get dispatcher.
     *
     * @return get methods map.
     */
    private Map<String, Function<HttpServletRequest, String>> getDispatcherInit() {
        this.getDispatch.put(LOAD, this.loadMakers());
        this.getDispatch.put(MAKER, this.loadModels());
        this.getDispatch.put(MODEL, this.loadYears());
        this.getDispatch.put(YEAR, this.loadGenerations());
        this.getDispatch.put(GEN, this.loadBody());
        this.getDispatch.put(BODY, this.loadDoors());
        this.getDispatch.put(FIRST, this.loadEngines());
        this.getDispatch.put(SECOND, this.loadDrive());
        this.getDispatch.put(ENGINE, this.loadGearBox());
        this.getDispatch.put(LOGIN, this.getLogin());
        this.getDispatch.put(HAVING_MAKER, this.havingMaker());
        return this.getDispatch;
    }

    /**
     * Having maker function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> havingMaker() {
        return req -> this.load(this.logic.getMakersOfAdd());
    }

    /**
     * Load years function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadYears() {
        return req -> this.load(this.logic.getYearsOfModel(req));
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    private Function<HttpServletRequest, String> getLogin() {
        return req -> req.getSession().getAttribute("login").toString();
    }

    /**
     * Load gear box function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadGearBox() {
        return req -> this.load(this.logic.getAllGearBox(req));
    }

    /**
     * Load drive function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadDrive() {
        return req -> this.load(this.logic.getAllDrive(req));
    }

    /**
     * Load engines function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadEngines() {
        return req -> this.load(this.logic.getAllEngines(req));
    }

    /**
     * Load doors function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadDoors() {
        return req -> this.load(this.logic.getAllDoors(req));
    }

    /**
     * Load body function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadBody() {
        return req -> this.load(this.logic.getAllBody(req));
    }

    /**
     * Load generations function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadGenerations() {
        return req -> this.load(this.logic.getAllGenerations(req));
    }

    /**
     * Load models function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadModels() {
        return req -> this.load(this.logic.getAllModels(req));
    }


    /**
     * Load makers function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> loadMakers() {
        return req -> this.load(this.logic.getAllMakers());
    }

    /**
     * Dispatcher do.
     *
     * @param dispatcher dispatcher map.
     * @param req        request
     * @return answer string
     */
    private String request(
            final Map<String, Function<HttpServletRequest, String>> dispatcher,
            final HttpServletRequest req) {
        return dispatcher.get(
                req.getParameterNames().nextElement()
        ).apply(req);
    }

    /**
     * Return all items as JSON. If not items return "".
     * "[{"id": 1,"name":"VW"}, {"id":2,"name":"BMW"}]".
     *
     * @param keyValueSet key/value set
     * @return JSON string
     */
    private String load(final List<KeyValue> keyValueSet) {
        String result = "[]";
        if (keyValueSet != null) {
            JSONStringer array = new JSONStringer();
            array.array();
            keyValueSet.forEach(key -> array
                    .object()
                    .key("id").value(key.getId())
                    .key("name").value(key.getName())
                    .endObject()
            );
            array.endArray();
            result = array.toString();
        }
        return result;
    }
}
