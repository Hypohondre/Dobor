package servlets;

import DAO.Repositories.UsersDaoImpl;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import services.Helper;
import services.RegistrationHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private Helper helper;
    private RegistrationHandler regService;
    private UsersDaoImpl udi;
    private PasswordEncoder passwordEncoder;
    Map<String, Object> root;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "registration.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String mail = req.getParameter("email");
        String date = req.getParameter("birth");


        ArrayList<String> errors = regService.validate(username, password, mail, date);
        root = new HashMap<>();

        if (errors.isEmpty()) {
            String encPassword = passwordEncoder.encode(password);
            User user = new User(username, password, mail, Date.valueOf(date));
            udi.save(user);
            resp.sendRedirect("/login");
        } else {
            root.put("errorMessage", errors);
            helper.render(req, resp, "registration.ftl", root);
            errors.clear();
        }
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        regService = new RegistrationHandler();
        udi = new UsersDaoImpl();
        passwordEncoder = new BCryptPasswordEncoder();
    }
}