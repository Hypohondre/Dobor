package servlets;

import DAO.Repositories.UsersDaoImpl;
import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;


@WebServlet("/edit-profile")
@MultipartConfig(location = "C:/Users/Рустем/IdeaProjects/Semestrovka-yes/src/main/webapp/images")
public class EditProfileServlet extends HttpServlet {
    private Helper helper;
    private UsersDaoImpl bd;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "edit-profile.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String mail = req.getParameter("email");
        String date = req.getParameter("birth");
        String photo = "";

        for(Part part : req.getParts()) {
            if (part.getName().equals("photo")) {
                photo = Math.random() + part.getSubmittedFileName();
                part.write(photo);
            }
        }

        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("user_id");

        User user = new User(username, password, mail, Date.valueOf(date));
        user.setId(id);
        user.setPhoto("../../webapp/images/" + photo);

        bd.update(user);
        resp.sendRedirect("/profile");
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        bd = new UsersDaoImpl();
    }
}