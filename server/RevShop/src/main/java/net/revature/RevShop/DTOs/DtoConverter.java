package net.revature.RevShop.DTOs;

import net.revature.RevShop.Models.Notification;

public class DtoConverter {

    public static NotificationDto toNotificationDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUser().getUserId());
        dto.setType(notification.getType().toString());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.getRead());
        dto.setCreatedAt(notification.getCreatedAt());
        return dto;

    }

}
