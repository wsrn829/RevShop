package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "CartItems")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cartItemId")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "buyerId")
    @Column(nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @Column(nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Column(nullable = false)
    private Product product;

    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Integer cartItemId, User buyer, User seller, Product product, Integer quantity) {
        this.cartItemId = cartItemId;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(User buyer, User seller, Product product, Integer quantity) {
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public @Min(1) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(1) Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}