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
import java.util.List;

/**
 * OrdersServlet
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class OrdersServlet extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(OrdersServlet.class);
    private final MainLogic logic = MainLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(this.allAdd());
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Return all items as JSON like.
     * "[{\"desc\": \"super\", \"id\": 11, \"done\": true}, {\"desc\": \"2\", \"id\": 12, \"done\": false}]".
     * If not items return "[]"
     *
     * @return JSON string
     */
    private String allAdd() {
        String result = "[]";
        List<Add> items = this.logic.getAllAdd();
        if (items != null) {
            result = JSONBuilder.build(items);
        }
        return result;
    }
}
