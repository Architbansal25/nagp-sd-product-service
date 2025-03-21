package com.nagp.webcart.products.controller;

import com.nagp.webcart.products.model.Product;
import com.nagp.webcart.products.model.Review;
import com.nagp.webcart.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{productId}/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long productId, @RequestBody Review review) {
        productService.addReview(productId, review);
        return ResponseEntity.ok("Review added successfully.");
    }
    @PutMapping("/{productId}/reviews/{reviewId}")
    public ResponseEntity<String> updateLikesDislikes(
            @PathVariable Long productId,
            @PathVariable Long reviewId,
            @RequestParam int likes,
            @RequestParam int dislikes) {
        productService.updateLikesDislikes(productId, reviewId, likes, dislikes);
        return ResponseEntity.ok("Review likes/dislikes updated successfully.");
    }
    @GetMapping("/{productId}/categories")
    public List<String> getCategories(@PathVariable Long productId) {
        return productService.getCategories(productId);
    }
}
