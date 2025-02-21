package ru.blaskowitz.userserviceadmin.service;

import ru.blaskowitz.userserviceadmin.model.User;

public interface UserService {
    User createUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);
}
