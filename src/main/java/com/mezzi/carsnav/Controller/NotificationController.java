package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.Notification;
import com.mezzi.carsnav.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Get all unread notifications
    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications() {
        return notificationService.getUnreadNotifications();
    }

    // Mark a single notification as read
    @PutMapping("/read/{notificationId}")
    public void markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
    }

    // Mark all notifications as read
    @PutMapping("/read")
    public void markAllAsRead() {
        notificationService.markAllAsRead();
    }
}
