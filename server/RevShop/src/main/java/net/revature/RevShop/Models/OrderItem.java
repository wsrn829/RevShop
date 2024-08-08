package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "OrderItems")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderItemId")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @Column(nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Column(nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Column(nullable = false)
    private Product product;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    @PositiveOrZero
    @Column(nullable = false)
    private Double unitPrice;


}