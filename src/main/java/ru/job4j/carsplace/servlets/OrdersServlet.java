package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carsplace.models.Add;
import ru.job4j.JSONBuilder;

import ru.job4j.carsplace.logic.MainLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * OrdersServlet
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class OrdersServlet extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(OrdersServlet.class);
    private static final String MAKER = "maker";
    private static final String HAS_PHOTO = "hasPhoto";
    private static final String SHOW_LAST_DAY = "showLastDay";
    private final MainLogic logic = MainLogic.getInstance();
    private final Map<String, Function<HttpServletRequest, String>> getDispatch = new HashMap<>();

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
     * Init get dispatcher.
     *
     * @return get methods map.
     */
    private Map<String, Function<HttpServletRequest, String>> getDispatcherInit() {
        this.getDispatch.put(MAKER, this.addOfMaker());
        this.getDispatch.put(HAS_PHOTO, this.addHasPhoto());
        this.getDispatch.put(SHOW_LAST_DAY, this.addOfLastDay());
        return this.getDispatch;
    }

    /**
     * Add of last day function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> addOfLastDay() {
        return req -> this.getResponse(this.logic.getAddOfLastDay());
    }

    /**
     * Add has photo function.
     *
     * @return the function
     */
    private Function<HttpServletRequest, String> addHasPhoto() {
        return req -> this.getResponse(this.logic.getAddHasPhoto());
    }

    /**
     * Return all items as JSON like.
     * "[{desc: \"super\", \"id\": 11, \"done\": true}, {\"desc\": \"2\", \"id\": 12, \"done\": false}]".
     * If not items return "[]"
     *
     * @return JSON string
     */
    private Function<HttpServletRequest, String> addOfMaker() {
        return req -> getResponse(this.logic.addOfMaker(req));
    }

    /**
     * Gets response.
     *
     * @param items the items
     * @return the response
     */
    private String getResponse(List<Add> items) {
        String result = "[]";
        if (items != null) {
            result = JSONBuilder.build(items);
        }
        return result;
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
}
