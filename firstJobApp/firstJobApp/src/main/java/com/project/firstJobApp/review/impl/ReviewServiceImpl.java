package com.project.firstJobApp.review.impl;

import com.project.firstJobApp.company.Company;
import com.project.firstJobApp.company.CompanyService;
import com.project.firstJobApp.review.Review;
import com.project.firstJobApp.review.ReviewRepository;
import com.project.firstJobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    public List<Review> findByCompanyId(Long companyId){
        List<Review> reviews =  reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company !=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter( review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = getReview(companyId,reviewId);
        if(review != null){
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompanyById(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        //companyService.getCompanyById(companyId)
        Review review = getReview(companyId,reviewId);
        if(review != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }


}
