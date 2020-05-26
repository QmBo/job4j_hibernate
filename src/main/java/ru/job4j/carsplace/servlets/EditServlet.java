package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carsplace.logic.MainLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * EditServlet
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.05.2020
 */
public class EditServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(EditServlet.class);
    private final MainLogic logic = MainLogic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("windows-1251");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        this.logic.changeStatus(req);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }
}
