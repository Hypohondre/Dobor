package DAO.Repositories;

import DAO.Interfaces.PostDao;
import models.Post;

import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {

    //language=SQL
    private final String FIND_POST_BY_ID = "SELECT * FROM post WHERE id = ?";

    @Override
    public Optional<Post> find(Long id) {
        return Optional.empty();
    }

    //language=SQL
    private final String SAVE = "INSERT INTO post(id, text, img, creator_id, category_id) values (default, ?,?,?,?)";

    @Override
    public void save(Post model) {

    }

    //language=SQL
    private final String UPDATE = "UPDATE post SET text=?, img=?, category_id=? WHERE id=?";

    @Override
    public void update(Post model) {

    }

    //language=SQL
    private final String DELETE = "DELETE FROM post WHERE id=?";

    @Override
    public void delete(Long id) {

    }

    //language=SQL
    private final String FIND_POST_BY_CREATOR = "SELECT * FROM post WHERE creator_id = ?";

    @Override
    public List<Post> findAllByCreator(Long id) {
        return null;
    }

    //language=SQL
    private final String FIND_POST_BY_CATEGORY = "SELECT * FROM post WHERE category_id = ?";

    @Override
    public List<Post> findAllByCategory(Long id) {
        return null;
    }

    //language=SQL
    private final String FIND_POST_BY_TEXT = "SELECT * FROM post WHERE text = ?";

    @Override
    public List<Post> findAll(String name) {
        return null;
    }
}