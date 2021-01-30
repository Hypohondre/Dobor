package DAO.Interfaces;

import models.Post;

import java.util.List;
import java.util.Optional;

public interface PostDao extends CrudDao<Post>{
    List<Post> findAll(Long id, String sql);
}