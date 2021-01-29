package DAO.Interfaces;

import models.Post;

import java.util.List;
import java.util.Optional;

public interface PostDao extends CrudDao<Post>{
    @Override
    Optional<Post> find(Long id);

    @Override
    void save(Post model);

    @Override
    void update(Post model);

    @Override
    void delete(Long id);

    List<Post> findAllByCreator(Long id);

    List<Post> findAllByCategory(Long id);
}