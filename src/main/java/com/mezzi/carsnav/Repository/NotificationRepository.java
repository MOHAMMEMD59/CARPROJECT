package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findBySeenFalse(); // Get only unread notifications
}

