package servlets;

import DAO.Interfaces.UsersDao;
import DAO.Repositories.UsersDaoImpl;
import models.User;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private Helper helper;
    private UsersDao udi;
    private Map<String, Object> root;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("user_id");
        sendUser(id);
        helper.render(req, resp, "profile.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void sendUser(Long id) {
        Optional<User> userCandidate =  udi.find(id);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            root.put("name", user.getUsername());
            root.put("birth", user.getBirthdate());
            root.put("email", user.getMail());
        }
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        udi = new UsersDaoImpl();
        root = new HashMap<>();
    }
}
