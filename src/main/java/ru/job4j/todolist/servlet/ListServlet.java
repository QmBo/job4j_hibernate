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
import java.util.Iterator;
import java.util.List;

/**
 * ListServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.04.2020
 */
public class ListServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ListServlet.class);
    private final MainLogic logic = MainLogic.getInstance();

    /**
     * Get all tasks.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(this.allTasks());
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Chang Done on Task.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        this.logic.doneChang(req);
    }

    /**
     * Return all items as JSON like. If not items return "".
     * "[{\"desc\": \"super\", \"id\": 11, \"done\": true}, {\"desc\": \"2\", \"id\": 12, \"done\": false}]".
     * @return JSON
     */
    private String allTasks() {
        List<Item> items = this.logic.allItem();
        StringBuilder sb = new StringBuilder();
        if (items != null) {
            sb.append("[");
            Iterator<Item> it = items.iterator();
            while (it.hasNext()) {
                Item item = it.next();
                sb.append(JSONBuilder.build(item));
                if (it.hasNext()) {
                    sb.append(", ");
                }
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
