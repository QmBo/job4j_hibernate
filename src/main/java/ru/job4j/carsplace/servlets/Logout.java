package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

/**
 * Logout
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Logout extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(Logout.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        resp.addCookie(new Cookie("login", ""));
        try {
            resp.sendRedirect(format("%s/carsplace", req.getContextPath()));
        } catch (Exception e) {
           LOG.error("Exception", e);
        }
    }
}
