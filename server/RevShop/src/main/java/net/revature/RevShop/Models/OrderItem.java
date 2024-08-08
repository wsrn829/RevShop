package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;
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

    @PositiveOrZero
    @Column(nullable = false)
    private Double unitPrice;

    public OrderItem() {
    }

    public OrderItem(Integer orderItemId, Order order, User seller, Product product, Integer quantity, Double unitPrice) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.seller = seller;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(Order order, User seller, Product product, Integer quantity, Double unitPrice) {
        this.order = order;
        this.seller = seller;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public @Min(1) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(1) Integer quantity) {
        this.quantity = quantity;
    }

    public @PositiveOrZero Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@PositiveOrZero Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}