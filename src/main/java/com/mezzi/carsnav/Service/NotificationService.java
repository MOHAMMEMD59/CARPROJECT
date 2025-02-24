package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Notification;
import com.mezzi.carsnav.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Get all unread notifications
    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findBySeenFalse();  // Fetch unread notifications
    }

    // Mark a single notification as read
    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));  // Handle not found error
        notification.setSeen(true);  // Mark the notification as read
        notificationRepository.save(notification);  // Save updated notification
    }

    // Mark all notifications as read
    public void markAllAsRead() {
        List<Notification> notifications = notificationRepository.findBySeenFalse();  // Fetch unread notifications
        notifications.forEach(notification -> notification.setSeen(true));  // Set each as read
        notificationRepository.saveAll(notifications);  // Save updated notifications
    }

    // Save a new notification
    public void createNotification(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
}
