package net.revature.RevShop.Controllers;


import net.revature.RevShop.DTOs.DtoConverter;
import net.revature.RevShop.DTOs.NotificationDto;
import net.revature.RevShop.Models.Notification;
import net.revature.RevShop.Models.User;
import net.revature.RevShop.Services.NotificationService;
import net.revature.RevShop.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {


    private final NotificationService notificationService;
    private final UserService userService;
    @Autowired
    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping
    public List<NotificationDto> getAllNotifications() {
        return notificationService.getAllNotifications().stream()
                .map(DtoConverter::toNotificationDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable Integer id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(value -> ResponseEntity.ok(DtoConverter.toNotificationDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<NotificationDto> getNotificationsByUserId(@PathVariable Integer userId) {
        return notificationService.getNotificationsByUserId(userId).stream()
                .map(DtoConverter::toNotificationDto)
                .toList();
    }

    @GetMapping("/read/{read}")
    public List<NotificationDto> getNotificationsByRead(@PathVariable Boolean read) {
        return notificationService.getNotificationsByRead(read).stream()
                .map(DtoConverter::toNotificationDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<NotificationDto> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(DtoConverter.toNotificationDto(savedNotification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable Integer id, @RequestBody Notification notificationDetails) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        if (notification.isPresent()) {
            notificationDetails.setNotificationId(id);
            Notification updatedNotification = notificationService.updateNotification(notificationDetails);
            return ResponseEntity.ok(DtoConverter.toNotificationDto(updatedNotification));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}