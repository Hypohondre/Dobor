package DAO.Repositories;

import DAO.Interfaces.CategoryDao;
import DAO.Interfaces.RowMapper;
import models.Category;
import models.Post;
import services.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl implements CategoryDao {
    public Connection connection;
    private RowMapper<Category> categoryRowMapper = row -> new Category(
            row.getString("name")
    );

    public CategoryDaoImpl() {
        this.connection = ConnectionService.getConnection();
    }

    //language=SQL
    private final String FIND = "SELECT * FROM category WHERE id=?";

    @Override
    public Optional<Category> find(Long id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            preparedStatement.setLong(1, id);

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
                return Optional.ofNullable(categoryRowMapper.mapRow(set));
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String SAVE = "INSERT INTO category(id, name) VALUES (default, ?)";

    @Override
    public void save(Category model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setString(1, model.getName());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
            try(ResultSet set = preparedStatement.getGeneratedKeys()) {
                if (set.next()) {
                    model.setId(set.getLong(1));
                } else {
                    throw new SQLException();
                }
                set.close();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String UPDATE = "UPDATE category SET name=? WHERE id=?";

    @Override
    public void update(Category model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setLong(2, model.getId());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String DELETE = "DELETE FROM category WHERE id=?";

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
    private final String FIND_ALL = "SELECT * FROM category WHERE name=?";

    @Override
    public List<Category> findAll(String name) {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            preparedStatement.setString(1, name);

            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Category category = categoryRowMapper.mapRow(set);
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return categories;
    }

    public Optional<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            preparedStatement.setString(1, name);

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
                Category category = new Category(set.getString("name"));
                category.setId(set.getLong(1));
                return Optional.ofNullable(category);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    //language=SQL
    private final String SELECT_ALL = "SELECT * FROM category";

    @Override
    public List<Category> selectAll() {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Category category = categoryRowMapper.mapRow(set);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return categories;
    }
}
