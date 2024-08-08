package net.revature.RevShop.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "CartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @PositiveOrZero
    private Integer quantity;


}