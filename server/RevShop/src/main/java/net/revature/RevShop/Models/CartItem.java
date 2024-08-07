package net.revature.RevShop.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "CartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;


}