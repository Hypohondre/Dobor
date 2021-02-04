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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/create-post")
public class CreatePostServlet extends HttpServlet {
    private Helper helper;
    private PostDaoImpl pdi;
    private CategoryDaoImpl cdi;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();

        List<Category> categories = cdi.selectAll();
        List<String> category = new ArrayList<>();

        for (Category category1 : categories) {
            category.add(category1.getName());
        }

        root.put("categories", category);

        helper.render(req, resp, "create-post.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        String category = req.getParameter("category");

        Long category_id = cdi.findByName(category).get().getId();
        HttpSession session = req.getSession();
        Long creator_id = (Long) session.getAttribute("user_id");

        Post post = new Post(name, text, creator_id, category_id);

        pdi.save(post);

        resp.sendRedirect("/list");
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        pdi = new PostDaoImpl();
        cdi = new CategoryDaoImpl();
    }
}
