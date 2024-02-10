package com.springboot.books.springbootbooksrestapi.repository;

import com.springboot.books.springbootbooksrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository interface for performing CRUD operations on Category entities.
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
