package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

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

    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;


}