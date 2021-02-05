package servlets;

import DAO.Repositories.CategoryDaoImpl;
import DAO.Repositories.PostDaoImpl;
import DAO.Repositories.UsersDaoImpl;
import models.Post;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    private Helper helper;
    private PostDaoImpl postDao;
    private CategoryDaoImpl categoryDao;
    private UsersDaoImpl usersDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"), 10);

        Map<String, Object> root = new HashMap<>();

        Optional<Post> postCandidate = postDao.find(id);

        if (postCandidate.isPresent()) {
            Post post = postCandidate.get();

            String name = post.getName();
            String text = post.getText();


            String category = categoryDao.find(post.getCategory_id()).get().getName();
            String creator = usersDao.find(post.getCreator_id()).get().getUsername();

            root.put("name",name);
            root.put("text",text);
            root.put("category",category);
            root.put("creator",creator);
        }
        root.put("id", id);

        helper.render(req,resp,"post.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        postDao = new PostDaoImpl();
        categoryDao = new CategoryDaoImpl();
        usersDao = new UsersDaoImpl();
    }
}
