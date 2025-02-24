package com.ali.reviewms.review.impl;


import com.ali.reviewms.review.Review;
import com.ali.reviewms.review.ReviewService;
import com.ali.reviewms.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findALlByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
    if (companyId != null && review != null) {
            review.setCompanyId(companyId);

            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review reviewUpdated) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {

            review.setTitle(reviewUpdated.getTitle());
            review.setDescription(reviewUpdated.getDescription());
            review.setRating(reviewUpdated.getRating());
            review.setCompanyId(reviewUpdated.getCompanyId());

            reviewRepository.save(review);

            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }

        return false;
    }
}