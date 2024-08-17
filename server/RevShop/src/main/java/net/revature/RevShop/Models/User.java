package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    @Column(unique = true, nullable = false, length = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,24}$",
            message = "username must be between 6 to 24 characters in length, and only letters and numbers are allowed.")
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(nullable = false, length = 255)
    @Pattern(regexp = "^[a-zA-Z](?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[A-Z][A-Za-z\\d\\-_!]){5,23}$",
            message = "password must be between 6 to 24 characters in length, start with a letter, and only permitted special characters are: -, _, !")
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;
    private String businessDetails;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    @Column(nullable = false, columnDefinition="BOOLEAN DEFAULT false") //Compatible with PostGres, but not all DB servers.
    private Boolean banned;

    public enum UserType {
        BUYER,
        SELLER,
        BOTH,
        ADMIN
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    // One to Many
    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<Product> sellerProducts = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<Order> buyerOrders = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<OrderItem> sellerOrderedItems = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<CartItem> buyerCartItems = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<CartItem> sellerCartItems = new HashSet<>();

    @OneToMany(mappedBy = "buyer")
    private Set<WishlistItem> buyerWishlistItems = new HashSet<>();

    @OneToMany(mappedBy = "seller")
    private Set<WishlistItem> sellerWishlistItems = new HashSet<>();

    public User() {}
    public User(String username, String email, String password, String firstName, String lastName, UserType type, String businessDetails) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.businessDetails = businessDetails;
    }

    public User(Integer userId, String username, String email, String password, String firstName, String lastName, UserType type, String businessDetails) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.businessDetails = businessDetails;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(String businessDetails) {
        this.businessDetails = businessDetails;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}