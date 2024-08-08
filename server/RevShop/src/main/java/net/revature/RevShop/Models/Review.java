package net.revature.RevShop.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reviews")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reviewId")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    @Column(nullable = false)
    private Product product;

    @Range(min = 1, max = 5)
    @Column(nullable = false)
    private Integer rating;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

}