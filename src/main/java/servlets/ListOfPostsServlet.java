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
import java.util.*;

@WebServlet("/list")
public class ListOfPostsServlet extends HttpServlet {
    private Helper helper;
    private PostDaoImpl postDao;
    private UsersDaoImpl usersDao;
    private CategoryDaoImpl categoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> root = new HashMap<>();
        List<Post> posts = postDao.selectAll();

        for(Post post : posts) {

                String creator = usersDao.find(post.getCreator_id()).get().getUsername();
                String category = categoryDao.find(post.getCategory_id()).get().getName();

                post.setCategory(category);
                post.setCreator(creator);
        }

        root.put("posts", posts);

        helper.render(req,resp,"list.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        postDao = new PostDaoImpl();
        usersDao = new UsersDaoImpl();
        categoryDao = new CategoryDaoImpl();
    }
}
