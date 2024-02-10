package com.springboot.books.springbootbooksrestapi.controller;

import com.springboot.books.springbootbooksrestapi.dto.BookDto;
import com.springboot.books.springbootbooksrestapi.payload.BookPaginationResponse;
import com.springboot.books.springbootbooksrestapi.service.BookService;
import com.springboot.books.springbootbooksrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
@Tag(
        name= "CRUD REST APIs for Book Resource"
)
public class BookController {

    private BookService bookService;


     //Create a new review for a specific book.
     //param bookId    The ID of the book for which the review is being created.
     //param reviewDto The review data provided in the request body.
     //return ResponseEntity containing the created ReviewDto and HTTP status 201 (CREATED).

    @Operation(
            summary = "Create Book REST API",
            description = "Creation of Book REST API in order to save books into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }


     //Get all reviews for a specific book.
     //param bookId The ID of the book for which reviews are being retrieved.
     //return List of ReviewDto representing all reviews for the specified book.
    @Operation(
            summary = "Get All Books REST API",
            description = "Get All the Books REST API in order to get all books from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public BookPaginationResponse getAllMovies(

            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required  = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir);
    }


      //Get a specific review by its ID for a specific book.
      //param bookId   The ID of the book containing the review.
      //param reviewId The ID of the review being retrieved.
      //return ResponseEntity containing the retrieved ReviewDto and HTTP status 200 (OK).
    @Operation(
            summary = "Get Book By Id REST API",
            description = "Get Book By Id REST API in order to get book by Id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable (name="id") long id) {
        return new ResponseEntity<BookDto>(bookService.getBookById(id), HttpStatus.OK);
    }


      //Update a specific review for a specific book.
      //param bookId    The ID of the book containing the review.
      //param reviewId  The ID of the review being updated.
      //param reviewDto The updated review data provided in the request body.
      //return ResponseEntity containing the updated ReviewDto and HTTP status 200 (OK).

    @Operation(
            summary = "Update Book REST API",
            description = "Update Book REST API in order to update books into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable(name="id") long id){
        return new ResponseEntity<>(bookService.updateBook(bookDto, id),HttpStatus.CREATED);
    }


     //Delete a specific review for a specific book.
     //param bookId   The ID of the book containing the review.
     //param reviewId The ID of the review being deleted.
     //return ResponseEntity containing a success message and HTTP status 200 (OK).

    @Operation(
            summary = "Delete Book REST API",
            description = "Delete Book REST API in order to delete book from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Basic Authentication"
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id") long id) {
        bookService.deleteMovieById(id);
        return new ResponseEntity<>("The book has been succesfully deleted.",HttpStatus.OK);
    }
}
