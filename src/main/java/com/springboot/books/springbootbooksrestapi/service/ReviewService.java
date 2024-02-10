package com.springboot.books.springbootbooksrestapi.service;

import com.springboot.books.springbootbooksrestapi.dto.ReviewDto;
import com.springboot.books.springbootbooksrestapi.entity.Review;

import java.util.List;

// Service interface for managing review-related operations.
public interface ReviewService {


     //Creates a new review associated with a book.
     //param bookId    The ID of the book for which the review is created.
     //param reviewDto The DTO representing the review to be created.
     //return The DTO representing the created review.
    ReviewDto createReview(long bookId, ReviewDto reviewDto);


     //Retrieves a list of reviews associated with a specific book.
     //param bookId The ID of the book for which to retrieve reviews.
     //return A list of DTOs representing the reviews associated with the book.
    List<ReviewDto> getReviewsByBookId(long bookId);


     //Retrieves a specific review associated with a book by its ID.
     //param bookId   The ID of the book.
     //param reviewId The ID of the review to retrieve.
     //return The DTO representing the retrieved review.
    ReviewDto getReviewById(long bookId, long reviewId);


     //Updates an existing review associated with a specific book.
     //param bookId        The ID of the book.
     //param reviewId      The ID of the review to update.
     //param reviewRequest The DTO representing the updated review information.
     //return The DTO representing the updated review.
    ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewRequest);


     //Deletes a review associated with a specific book by its ID.
     //param bookId   The ID of the book.
     //param reviewId The ID of the review to delete.
    void deleteReview(long bookId, long reviewId);
}

