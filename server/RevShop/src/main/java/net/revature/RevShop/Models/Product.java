package net.revature.RevShop.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User seller;

    private String name;
    private String description;

    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer stock;

    @PositiveOrZero
    private Integer thresholdStock;

    private String img_url;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<WishlistItem> wishlistItems = new HashSet<>();

}