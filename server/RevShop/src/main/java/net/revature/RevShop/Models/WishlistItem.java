package net.revature.RevShop.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "WishlistItems")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}