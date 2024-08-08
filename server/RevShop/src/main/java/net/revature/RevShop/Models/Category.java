package net.revature.RevShop.Models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();


}