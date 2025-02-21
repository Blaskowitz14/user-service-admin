package ru.blaskowitz.userserviceadmin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.blaskowitz.userserviceadmin.event.UserDeletedEvent;
import ru.blaskowitz.userserviceadmin.exception.UserCreationException;
import ru.blaskowitz.userserviceadmin.exception.UserNotFoundException;
import ru.blaskowitz.userserviceadmin.model.User;
import ru.blaskowitz.userserviceadmin.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public User createUser(User user) {
        log.info("Creating user: {}", user);
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserCreationException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#id")
    public User getUser(Long id) {
        return userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user: {}", id);
        User userToDelete = getUser(id);
        userToDelete.setIsDeleted(true);
        userRepository.save(userToDelete);
        eventPublisher.publishEvent(new UserDeletedEvent(this, id));
    }
}
