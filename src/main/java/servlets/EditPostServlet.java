package servlets;

import DAO.Repositories.CategoryDaoImpl;
import DAO.Repositories.PostDaoImpl;
import models.Category;
import models.Post;
import org.springframework.security.core.parameters.P;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/edit-post")
public class EditPostServlet extends HttpServlet {
    private Helper helper;
    private PostDaoImpl postDao;
    private CategoryDaoImpl categoryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();

        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Post> postCandidate = postDao.find(id);

        if (postCandidate.isPresent()) {
            Post post = postCandidate.get();

            String name = post.getName();
            String text = post.getText();

            List<Category> categories = categoryDao.selectAll();
            List<String> category = new ArrayList<>();

            for (Category category1 : categories) {
                category.add(category1.getName());
            }

            root.put("name", name);
            root.put("text", text);
            root.put("categories", category);
            root.put("id", id);
        }

        helper.render(req,resp,"edit-post.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        String category = req.getParameter("category");

        Long category_id = categoryDao.findByName(category).get().getId();

        Post post = new Post(text, name, null, category_id);
        post.setId(Long.parseLong(req.getParameter("id")));

        postDao.update(post);

        resp.sendRedirect("/list");
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        postDao = new PostDaoImpl();
        categoryDao = new CategoryDaoImpl();
    }
}
