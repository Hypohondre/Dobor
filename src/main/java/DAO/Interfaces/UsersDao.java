package DAO.Interfaces;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UsersDao extends CrudDao<User> {
    Optional<Long> login(String name, String password);
}
