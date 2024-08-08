package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "WishlistItems")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "wishlistItemId")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistItemId;

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

}