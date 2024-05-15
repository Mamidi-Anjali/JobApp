package com.project.firstJobApp.review;

import java.util.List;

public interface ReviewService {
    List<Review> findByCompanyId(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long companyId,Long reviewId);
    boolean deleteReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review updatedReview);
}
