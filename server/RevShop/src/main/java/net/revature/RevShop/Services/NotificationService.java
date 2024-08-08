package net.revature.RevShop.Services;

import net.revature.RevShop.Models.Notification;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {


    private final NotificationRepository notificationRepository;

    private final UserService userService;

    private final EmailService emailService;
    @Autowired
    public NotificationService(NotificationRepository notificationRepository, EmailService emailService, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Integer notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public List<Notification> getNotificationsByUserId(Integer userId) {
        return notificationRepository.findByUser_userId(userId);
    }

    public void sendEmailNotification(Notification notification) {
        String subject = "New Notification: " + notification.getType().name();
        String content = notification.getMessage();
        emailService.sendSimpleMessage(notification.getUser().getEmail(), subject, content);
    }

    public Notification createNotification(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification must not be null");
        }
        User user = userService.getUserById(notification.getUser().getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Notification savedNotification = new Notification(user, notification.getType(), notification.getMessage());
        sendEmailNotification(savedNotification);
        return notificationRepository.save(savedNotification);
    }



    public Notification updateNotificationReadStatus(Integer notificationId, Boolean read) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(read);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Integer notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}