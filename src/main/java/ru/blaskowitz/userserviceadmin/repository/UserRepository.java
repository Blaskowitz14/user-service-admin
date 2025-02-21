package ru.blaskowitz.userserviceadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blaskowitz.userserviceadmin.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndIsDeletedFalse(Long id);

    boolean existsByUsername(String username);
}
