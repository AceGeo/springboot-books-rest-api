package com.springboot.books.springbootbooksrestapi.controller;

import com.springboot.books.springbootbooksrestapi.dto.ReviewDto;
import com.springboot.books.springbootbooksrestapi.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Tag(
        name= "CRUD REST APIs for Reviews of the Books"
)
//Creation of Review CRUD endpoints
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    //Create a new review for a specific book.
    //param bookId    The ID of the book for which the review is being created.
    //param reviewDto The review data provided in the request body.
    //return ResponseEntity containing the created ReviewDto and HTTP status 201 (CREATED).
    @Operation(
            summary = "Post reviews for the Books by Id REST APIs",
            description = "Creation of Review for Book REST API in order to save the reviews of Books by Id into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "bookId") long bookId,
                                                  @Valid
                                                  @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(bookId,reviewDto), HttpStatus.CREATED);
    }

    //Get all reviews for a specific book.
    //param bookId The ID of the book for which reviews are being retrieved.
    //return List of ReviewDto representing all reviews for the specified book.
    @Operation(
            summary = "Get All Reviews REST API",
            description = "Get All the Reviews by Book Id REST API in order to get all Reviews by Book Id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/books/{bookId}/reviews")
    public List<ReviewDto> getReviewsByBookId(@PathVariable(value = "bookId") long bookId){
        return reviewService.getReviewsByBookId(bookId);
    }

    //Get a specific review by its ID for a specific book.
    //param bookId   The ID of the book containing the review.
    //param reviewId The ID of the review being retrieved.
    //return ResponseEntity containing the retrieved ReviewDto and HTTP status 200 (OK).
    @Operation(
            summary = "Get All Reviews REST API",
            description = "Get All the Reviews REST API in order to get all Reviews from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value="bookId") long bookId,
                                                   @PathVariable(value="reviewId") long reviewId){

        ReviewDto reviewDto = reviewService.getReviewById(bookId,reviewId);
        return new ResponseEntity<>(reviewDto,HttpStatus.OK);
    }


    //Update a specific review for a specific book.
    //param bookId    The ID of the book containing the review.
    //param reviewId  The ID of the review being updated.
    //param reviewDto The updated review data provided in the request body.
    //return ResponseEntity containing the updated ReviewDto and HTTP status 200 (OK).
    @Operation(
            summary = "Update Review REST API",
            description = "Update Review REST API in order to update reviews into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "bookId") long bookId,
                                                  @PathVariable(value = "reviewId") long reviewId,
                                                  @RequestBody ReviewDto reviewDto){
        ReviewDto updatedReview = reviewService.updateReview(bookId,reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview,HttpStatus.OK);
    }
    //Delete a specific review for a specific book.
    //param bookId   The ID of the book containing the review.
    //param reviewId The ID of the review being deleted.
    //return ResponseEntity containing a success message and HTTP status 200 (OK).
    @Operation(
            summary = "Delete Review REST API",
            description = "Delete Review REST API in order to delete reviews from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "bookId") long bookId,
                                               @PathVariable(value = "reviewId") long reviewId){

        reviewService.deleteReview(bookId,reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}