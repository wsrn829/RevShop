package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Column(nullable = false)
    private User seller;

    @Column(nullable = false)
    private String name;

    private String description;

    @PositiveOrZero
    @Column(nullable = false)
    private Double price;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer stock;

    @PositiveOrZero
    @Column(nullable = false)
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