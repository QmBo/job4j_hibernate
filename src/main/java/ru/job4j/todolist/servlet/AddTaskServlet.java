package ru.job4j.todolist.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.JSONBuilder;
import ru.job4j.todolist.logic.MainLogic;
import ru.job4j.todolist.models.Item;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AddTaskServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class AddTaskServlet extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(AddTaskServlet.class);
    private final MainLogic logic = MainLogic.getInstance();

    /**
     * Add new Task to server.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(this.addTask(req));
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Add task to server and return JSON item with id.
     * "[{\"desc\": \"super\", \"id\": 11, \"done\": true}]".
     * @param req request
     * @return JSON
     */
    private String addTask(final HttpServletRequest req) {
        Item item = this.logic.addItem(req);
        StringBuilder sb = new StringBuilder();
        if (item != null) {
            sb.append("[");
            sb.append(JSONBuilder.build(item));
            sb.append("]");
        }
        return sb.toString();
    }
}
