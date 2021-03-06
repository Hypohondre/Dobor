package DAO.Repositories;

import DAO.Interfaces.PostDao;
import DAO.Interfaces.RowMapper;
import models.Post;
import services.ConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDaoImpl implements PostDao {
    public Connection connection;
    private RowMapper<Post> postRowMapper = row -> new Post(
            row.getString("text"),
            row.getString("name"),
            row.getLong("creator_id"),
            row.getLong("category_id")
    );

    public PostDaoImpl() {
        this.connection = ConnectionService.getConnection();
    }

    //language=SQL
    private final String FIND_POST_BY_ID = "SELECT * FROM post WHERE id = ?";

    @Override
    public Optional<Post> find(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_POST_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
                return Optional.ofNullable(postRowMapper.mapRow(set));
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String SELECT_ALL = "SELECT * FROM post";

    public List<Post> selectAll() {
        List<Post> posts = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Post post = postRowMapper.mapRow(set);
                post.setId(set.getLong(1));
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return posts;
    }

    //language=SQL
    private final String SAVE = "INSERT INTO post(id,name, text, img, creator_id, category_id) values (default,?,?,?,?,?)";

    @Override
    public void save(Post model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, model.getText());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getImg());
            preparedStatement.setLong(4, model.getCreator_id());
            preparedStatement.setLong(5, model.getCategory_id());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("zdes");
            }
            try(ResultSet set = preparedStatement.getGeneratedKeys()) {
                if (set.next()) {
                    model.setId(set.getLong(1));
                } else {
                    throw new SQLException("tut");
                }
                set.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String UPDATE = "UPDATE post SET name=?, text=?, category_id=? WHERE id=?";

    @Override
    public void update(Post model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1,model.getName());
            preparedStatement.setString(2,model.getText());
            preparedStatement.setLong(3,model.getCategory_id());
            preparedStatement.setLong(4,model.getId());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String DELETE = "DELETE FROM post WHERE id=?";

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    public static final String FIND_POST_BY_CREATOR = "SELECT * FROM post WHERE creator_id = ?";

    //language=SQL
    public static final String FIND_POST_BY_CATEGORY = "SELECT * FROM post WHERE category_id = ?";

    @Override
    public List<Post> findAll(Long id, String sql) {
        List<Post> posts = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Post post = postRowMapper.mapRow(set);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return posts;
    }

    //language=SQL
    private final String FIND_POST_BY_TEXT = "SELECT * FROM post WHERE text = ?";

    @Override
    public List<Post> findAll(String name) {
        return null;
    }
}