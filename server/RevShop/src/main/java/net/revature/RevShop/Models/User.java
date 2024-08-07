package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String userType;
    private String businessDetails;
    private Boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    /*
    * @OneToMany
    * orders (buyers)
    * reviews (users)
    * cartItems (buyers)
    * cartItems (sellers)
    * wishlistItems (buyers)
    * wishlistItems (sellers)
    * orderItems (sellers)
    * products (sellers)
    * */


}