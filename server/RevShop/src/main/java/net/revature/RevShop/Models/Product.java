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
@Table(name = "products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;



    @JoinColumn(nullable = false, name = "sellerId")
    @ManyToOne(fetch = FetchType.EAGER)
  
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

    public Product() {
    }

    public Product(Integer productId, User seller, String name, String description, Double price, Integer stock, Integer thresholdStock, String img_url, Category category) {
        this.productId = productId;
        this.seller = seller;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.thresholdStock = thresholdStock;
        this.img_url = img_url;
        this.category = category;
    }

    public Product(User seller, String name, String description, Double price, Integer stock, Integer thresholdStock, String img_url, Category category) {
        this.seller = seller;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.thresholdStock = thresholdStock;
        this.img_url = img_url;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @PositiveOrZero Double getPrice() {
        return price;
    }

    public void setPrice(@PositiveOrZero Double price) {
        this.price = price;
    }

    public @PositiveOrZero Integer getStock() {
        return stock;
    }

    public void setStock(@PositiveOrZero Integer stock) {
        this.stock = stock;
    }

    public @PositiveOrZero Integer getThresholdStock() {
        return thresholdStock;
    }

    public void setThresholdStock(@PositiveOrZero Integer thresholdStock) {
        this.thresholdStock = thresholdStock;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(Set<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }
}