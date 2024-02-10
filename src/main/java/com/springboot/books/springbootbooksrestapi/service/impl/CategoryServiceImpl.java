package com.springboot.books.springbootbooksrestapi.service.impl;

import com.springboot.books.springbootbooksrestapi.dto.CategoryDto;
import com.springboot.books.springbootbooksrestapi.entity.Category;
import com.springboot.books.springbootbooksrestapi.exception.ResourceNotFoundException;
import com.springboot.books.springbootbooksrestapi.repository.CategoryRepository;
import com.springboot.books.springbootbooksrestapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    // Implementation of the addCategory method from the CategoryService interface
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        // Map CategoryDto to Category entity
        Category category = modelMapper.map(categoryDto,Category.class);

        // Save the category to the database
        Category savedCategory = categoryRepository.save(category);

        // Map the saved category back to a CategoryDto
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    // Implementation of the getCategory method from the CategoryService interface
    @Override
    public CategoryDto getCategory(Long categoryId) {

        // Find the category by categoryId
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));

        // Map the updated category back to a CategoryDto
        return modelMapper.map(category, CategoryDto.class);
    }

    // Implementation of the getAllCategories method from the CategoryService interface
    @Override
    public List<CategoryDto> getAllCategories() {

        // List the categories in order to show them to the user
        List<Category> categories = categoryRepository.findAll();

        // Map the categories and collect to a list
        return categories
                .stream()
                .map((category) ->
                        modelMapper
                                .map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    // Implementation of the updateCategory method from the CategoryService interface
    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {

        // Find the category by categoryId
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category","id", categoryId));

        // Update the categories properties with values from the DTO
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        // Save the updated category to the database
        Category updatedCategory = categoryRepository.save(category);

        // Map the updated category back to a CategoryDto
        return modelMapper.map(category, CategoryDto.class);
    }
    // Implementation of the deleteCategory method from the CategoryService interface
    @Override
    public void deleteCategory(Long categoryId) {

        // Find the category by categoryId
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category","id", categoryId));

        // Delete the category from the database
        categoryRepository.delete(category);
    }
}
