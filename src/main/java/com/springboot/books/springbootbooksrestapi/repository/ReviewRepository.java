package com.springboot.books.springbootbooksrestapi.repository;

import com.springboot.books.springbootbooksrestapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//Repository interface for performing CRUD operations on Review entities.
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Finds a list of reviews by the ID of the associated book.
     *
     * @param bookId The ID of the book for which to retrieve reviews.
     * @return A list of reviews associated with the specified book ID.
     */
    List<Review> findByBookId(long bookId);

}
