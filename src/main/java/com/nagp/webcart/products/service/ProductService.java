package com.nagp.webcart.products.service;

import com.nagp.webcart.products.exception.customException;
import com.nagp.webcart.products.model.Product;
import com.nagp.webcart.products.model.Review;
import com.nagp.webcart.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        logger.info("product has been created against the id {}",product.getProductId());
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long productId) {
       // return productRepository.findById(productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new customException("Product not found with id: " + productId));
    }
    public void deleteProduct(Long productId) {
        logger.info("request to delete the product with id {}",productId);
        productRepository.deleteById(productId);
    }
    public void addReview(Long productId, Review review) {
        Product product = getProductById(productId);
        product.getReviews().add(review);
        productRepository.save(product);
    }

    public void updateLikesDislikes(Long productId, Long reviewId, int likes, int dislikes) {
        Product product = getProductById(productId);
        Review review = product.getReviews().stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
        review.setLikes(likes);
        review.setDislikes(dislikes);
        productRepository.save(product);
    }

    public List<String> getCategories(Long productId) {
        Product product = getProductById(productId);
        return product.getCategories();
    }
}
