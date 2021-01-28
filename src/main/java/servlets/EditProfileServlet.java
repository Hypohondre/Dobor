package servlets;

import DAO.Repositories.UsersDaoImpl;
import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

public class EditProfileServlet extends HttpServlet {
    private Helper helper;
    private UsersDaoImpl bd;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "edit-profile", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String mail = req.getParameter("email");
        String date = req.getParameter("birth");

        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("user_id");

        User user = new User(username, password, mail, Date.valueOf(date));
        user.setId(id);

        bd.update(user);
        resp.sendRedirect("/profile");
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        bd = new UsersDaoImpl();
    }
}
