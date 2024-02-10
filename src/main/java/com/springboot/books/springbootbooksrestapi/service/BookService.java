package com.springboot.books.springbootbooksrestapi.service;

import com.springboot.books.springbootbooksrestapi.dto.BookDto;
import com.springboot.books.springbootbooksrestapi.payload.BookPaginationResponse;


// Service interface for managing book-related operations.
public interface BookService {


    //Creates a new book.
    //param bookDto The DTO representing the book to be created.
    //return The DTO representing the created book.
    BookDto createBook(BookDto bookDto);

     //Retrieves a paginated list of all books.
     //param pageNo   The page number.
     //param pageSize The number of books to include per page.
     //param sortBy   The field by which to sort the books.
     //param sortDir  The sort direction (ASC or DESC).
     //return A paginated response containing a list of books.
    BookPaginationResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir);

     //Retrieves a specific book by its ID.
     //param id The ID of the book to retrieve.
     //return The DTO representing the retrieved book.
    BookDto getBookById(long id);


     //Updates an existing book.
     //param bookDto The DTO representing the updated book information.
     //param id      The ID of the book to update.
     //return The DTO representing the updated book.
    BookDto updateBook(BookDto bookDto, long id);

     //Deletes a book by its ID.
     //param id The ID of the book to delete.
    void deleteMovieById(long id);
}

