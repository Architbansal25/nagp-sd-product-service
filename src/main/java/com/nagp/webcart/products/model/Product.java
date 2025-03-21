package com.nagp.webcart.products.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductId;

    private String name;
    private String description;
    private String brand;
    private double price;
    private double originalPrice;
    private String discount;
    private double rating;
    private int reviewsCount;
    private String currency;
    private int stock;
    private String image;

    @ElementCollection
    private List<String> categories;

    @ElementCollection
    private List<String> sizes;

    @Embedded
    private Specification specification;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void calculatePrice() {
        double discountValue = 0;
        if (this.discount != null && this.discount.endsWith("% OFF")) {
            discountValue = Double.parseDouble(this.discount.replace("% OFF", "").trim());
        }
        this.price = Math.round(this.originalPrice - (this.originalPrice * (discountValue / 100)));
    }
}
