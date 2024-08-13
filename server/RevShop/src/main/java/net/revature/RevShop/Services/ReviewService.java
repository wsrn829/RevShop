package net.revature.RevShop.Services;

import net.revature.RevShop.Models.*;
import net.revature.RevShop.Repositories.ReviewRepository;
import net.revature.RevShop.Repositories.ProductRepository;
import net.revature.RevShop.Repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Review> getAllProductReviews(Integer productId) {
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> getAllUserReviews(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review getReviewById(Integer reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review editReview(Review updatedReview) {
        //Review
        Review currReview = getReviewById(updatedReview.getReviewId());
        currReview.setComment(updatedReview.getComment());
        currReview.setRating(updatedReview.getRating());
        return reviewRepository.save(currReview);
    }

    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

}