package ru.job4j.carsplace.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carsplace.logic.MainLogic;

import javax.servlet.http.*;

import java.io.IOException;

import static java.lang.String.format;

/**
 * Login
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.05.2020
 */
public class Login extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(Login.class);
    private static final String LOGIN = "login";
    private final MainLogic service = MainLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } catch (Exception e) {
            LOG.error("Represent Error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (this.service.isCredentials(req)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", req.getParameter(LOGIN));
            resp.addCookie(
                    new Cookie(
                            "login",
                            String.valueOf(
                                    this.service.getUserByLogin(req).getId()
                            )
                    )
            );
            try {
                resp.sendRedirect(format("%s/carsplace/", req.getContextPath()));
            } catch (IOException e) {
                LOG.error("IOException", e);
            }
        } else {
            req.setAttribute("error", "Credential error!");
            doGet(req, resp);
        }
    }
}
