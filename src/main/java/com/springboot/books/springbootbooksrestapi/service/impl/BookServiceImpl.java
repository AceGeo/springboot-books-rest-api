package com.springboot.books.springbootbooksrestapi.service.impl;

import com.springboot.books.springbootbooksrestapi.dto.BookDto;
import com.springboot.books.springbootbooksrestapi.entity.Book;
import com.springboot.books.springbootbooksrestapi.exception.ResourceNotFoundException;
import com.springboot.books.springbootbooksrestapi.payload.BookPaginationResponse;
import com.springboot.books.springbootbooksrestapi.repository.BookRepository;
import com.springboot.books.springbootbooksrestapi.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    // Implementation of the createBook method from the BookService interface
    @Override
    public BookDto createBook(BookDto bookDto) {

        // Create Dto to Entity
        Book book = mapTOEntity(bookDto);
        Book newBook = bookRepository.save(book);

        // Create Entity to Dto
        BookDto bookResponse = mapTODTO(newBook);
        return bookResponse;

    }

    // Implementation of the getAllBooks method from the BookService interface
    @Override
    public BookPaginationResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);

        // get content for page object via map
        List<Book> booksList = books.getContent();
        List<BookDto> content = booksList.stream()
                .map(book -> mapTODTO(book)).collect(Collectors.toList());

        // Create and populate BookPaginationResponse object
        BookPaginationResponse bookPaginationResponse = new BookPaginationResponse();
        bookPaginationResponse.setContent(content);
        bookPaginationResponse.setPageNo(books.getNumber());
        bookPaginationResponse.setPageSize(books.getSize());
        bookPaginationResponse.setTotalElements(books.getTotalElements());
        bookPaginationResponse.setPageSize(books.getSize());
        bookPaginationResponse.setLast(books.isLast());

        return bookPaginationResponse;
    }

    // Implementation of the getBookById method from the BookService interface
    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Book","id",id));
        return mapTODTO(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, long id) {

        //Get movie by the id from the database
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book","id",id));

        // Update book properties with values from the DTO
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setLanguage(bookDto.getLanguage());
        book.setPages(bookDto.getPages());
        book.setReleaseDate(bookDto.getReleaseDate());

        // Save the updated book to the database
        Book updateBook = bookRepository.save(book);
        return mapTODTO(updateBook);
    }

    // Implementation of the deleteMovieById method from the BookService interface
    @Override
    public void deleteMovieById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book","id",id));
        bookRepository.delete(book);
    }


    // convert Entity to DTO
    private BookDto mapTODTO(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }
    // convert Dto to Entity
    private Book mapTOEntity(BookDto bookDto){
        Book book = modelMapper.map(bookDto,Book.class);
        return book;
    }

}