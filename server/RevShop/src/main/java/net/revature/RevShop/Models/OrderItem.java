package net.revature.RevShop.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

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

    @PositiveOrZero
    private Integer quantity;

    @PositiveOrZero
    private Double unitPrice;


}