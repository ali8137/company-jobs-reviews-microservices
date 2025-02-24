package com.ali.reviewms.review;

import com.ali.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMessageProducer reviewMessageProducer;

    public ReviewController(
            ReviewService reviewService,
            ReviewMessageProducer reviewMessageProducer
    ) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(
            @RequestParam Long companyId
    ) {
        List<Review> reviews = reviewService.getAllReviews(companyId);

        if (reviews != null) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addReview(
            @RequestParam Long companyId,
            @RequestBody Review review
    ) {
        boolean companyExists = reviewService.addReview(companyId, review);

        if (companyExists) {
            reviewMessageProducer.sendMessage(review);

            return new ResponseEntity<>("review added successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        Review review = reviewService.getReview(reviewId);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long reviewId,
            @RequestBody Review reviewUpdated
    ) {
        boolean isReviewUpdated = reviewService.updateReview(reviewId, reviewUpdated);

        if (isReviewUpdated) {
            return new ResponseEntity<>("review updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("review is not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);

        if (isReviewDeleted) {
            return new ResponseEntity<>("review deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("review is not deleted", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId) {
        List<Review> reviewList = reviewService.getAllReviews(companyId);

        return reviewList.stream().mapToDouble(Review::getRating).average()
                .orElse(0.0);
    }
}