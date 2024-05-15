package com.project.firstJobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.findByCompanyId(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewAdded = reviewService.addReview(companyId,review);
        if(isReviewAdded)
            return new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Review Not Saved Because company Not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId) , HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview){

        boolean isUpdated = reviewService.updateReview(companyId,reviewId,updatedReview);
        if(isUpdated)
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Review Id or Company Id Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReview(companyId,reviewId);
        if(isDeleted)
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Review Id or Company Id Not Found", HttpStatus.NOT_FOUND);

        }

}
