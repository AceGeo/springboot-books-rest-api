package com.springboot.books.springbootbooksrestapi.service;

import com.springboot.books.springbootbooksrestapi.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    //Add Category.
    //param categoryDto The CategoryDto containing category data.
    //return The created CategoryDto.
    CategoryDto addCategory(CategoryDto categoryDto);

    //Get Category by ID.
    //param categoryId The ID of the category to retrieve.
    //return The retrieved CategoryDto.
    CategoryDto getCategory(Long categoryId);


    //Get all Categories.
    //return The list of all CategoryDtos.
    List<CategoryDto> getAllCategories();


     //Update Category.
     //param categoryId  The ID of the category to update.
     //param categoryDto The updated category data.
     //return The updated CategoryDto.
    CategoryDto updateCategory(long categoryId, CategoryDto categoryDto);


     //Delete Category by ID.
     //param categoryId The ID of the category to delete.
    void deleteCategory(Long categoryId);

}
