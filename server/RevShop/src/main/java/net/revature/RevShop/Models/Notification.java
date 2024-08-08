package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "Notifications")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "notificationId")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Column(nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    public enum NotificationType {
        ORDER_PLACED,
        SHIP_ITEM,
        STOCK_ALERT,
        PRICE_CHANGE
    }

    private String content;

    @Column(nullable = false)
    private Boolean isRead;

    @CreationTimestamp
    private LocalDateTime createdAt;



}