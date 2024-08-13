package net.revature.RevShop.Controllers;


import net.revature.RevShop.Models.*;
import net.revature.RevShop.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reviews")
@CrossOrigin
public class ReviewController{
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Integer productId){
        return reviewService.getAllProductReviews(productId);
    }

    @GetMapping("/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Integer userId){
        return reviewService.getAllUserReviews(userId);
    }

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable Integer reviewId){
        return reviewService.getReviewById(reviewId);
    }

    //@PostMapping

    @PutMapping("/update_review")
    public Review updateReview(@RequestBody Review review){
        return reviewService.editReview(review);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReviewById(@PathVariable Integer reviewId){
        reviewService.deleteReview(reviewId);
    }
}
