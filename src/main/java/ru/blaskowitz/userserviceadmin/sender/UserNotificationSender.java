package ru.blaskowitz.userserviceadmin.sender;

public interface UserNotificationSender {
    void sendUserDeletedNotification(Long userId);
}
