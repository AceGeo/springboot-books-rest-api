package com.springboot.books.springbootbooksrestapi.repository;

import com.springboot.books.springbootbooksrestapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


//Repository interface for performing CRUD operations on Book entities.
public interface BookRepository extends JpaRepository<Book,Long> {
}
