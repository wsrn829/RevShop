package net.revature.RevShop.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    // number can't be negative
    private Integer quantity;

    // number can't be negative
    private Double unitPrice;


}