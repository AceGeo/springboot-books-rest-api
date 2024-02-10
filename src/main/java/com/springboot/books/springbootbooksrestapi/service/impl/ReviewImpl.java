package com.springboot.books.springbootbooksrestapi.service.impl;

import com.springboot.books.springbootbooksrestapi.dto.ReviewDto;
import com.springboot.books.springbootbooksrestapi.entity.Book;
import com.springboot.books.springbootbooksrestapi.entity.Review;
import com.springboot.books.springbootbooksrestapi.exception.BlogAPIException;
import com.springboot.books.springbootbooksrestapi.exception.ResourceNotFoundException;
import com.springboot.books.springbootbooksrestapi.repository.BookRepository;
import com.springboot.books.springbootbooksrestapi.repository.ReviewRepository;
import com.springboot.books.springbootbooksrestapi.service.ReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    // Implementation of the createReview method from the ReviewService interface

    @Override
    public ReviewDto createReview(long bookId, ReviewDto reviewDto) {

        // Map ReviewDto to Review entity
        Review review = mapToEntity(reviewDto);

        // Find the associated book by bookId
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId));

        // Set the book for the review
        review.setBook(book);

        // Save the review to the database
        Review newReview = reviewRepository.save(review);

        // Map the saved review back to a ReviewDto
        return mapToDTO(newReview);
    }

    // Implementation of the getReviewsByBookId method from the ReviewService interface
    @Override
    public List<ReviewDto> getReviewsByBookId(long bookId) {
        // Find reviews associated with the specified bookId
        List<Review> reviews = reviewRepository.findByBookId(bookId);

        // Map each Review entity to a ReviewDto and collect into a list
        return reviews.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Implementation of the getReviewById method from the ReviewService interface
    @Override
    public ReviewDto getReviewById(long bookId, long reviewId) {
        // Find the associated book by bookId
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId));

        // Find the review by reviewId
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ResourceNotFoundException("Review", "id", reviewId));

        // Check if the review belongs to the specified book
        if (!review.getBook().getId().equals(book.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Review does not belong to this book");
        }

        // Map the found review to a ReviewDto
        return mapToDTO(review);
    }

    // Implementation of the updateReview method from the ReviewService interface
    @Override
    public ReviewDto updateReview(long bookId, long reviewId, ReviewDto reviewRequest) {

        // Find the associated book by bookId
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId));

        // Find the review by reviewId
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ResourceNotFoundException("Review", "id", reviewId));

        // Check if the review belongs to the specified book
        if (!review.getBook().getId().equals(book.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Review does not belong to this book");
        }

        // Update the review properties with values from the DTO
        review.setName(reviewRequest.getName());
        review.setEmail(reviewRequest.getEmail());
        review.setBody(reviewRequest.getBody());
        review.setRating(reviewRequest.getRating());

        // Save the updated review to the database
        Review updatedReview = reviewRepository.save(review);

        // Map the updated review back to a ReviewDto
        return mapToDTO(updatedReview);
    }

    // Implementation of the deleteReview method from the ReviewService interface
    @Override
    public void deleteReview(long bookId, long reviewId) {

        // Find the associated book by bookId
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "id", bookId));

        // Find the review by reviewId
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ResourceNotFoundException("Review", "id", reviewId));

        // Check if the review belongs to the specified book
        if (!review.getBook().getId().equals(book.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Review does not belong to this book");
        }

        // Delete the review from the database
        reviewRepository.delete(review);
    }

    // convert Entity to DTO
    private ReviewDto mapToDTO(Review review) {
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
        return  reviewDto;
    }

    // convert Dto to Entity
    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        return review;
    }
}
