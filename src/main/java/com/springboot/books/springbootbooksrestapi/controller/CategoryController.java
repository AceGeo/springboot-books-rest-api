package com.springboot.books.springbootbooksrestapi.controller;

import com.springboot.books.springbootbooksrestapi.dto.CategoryDto;
import com.springboot.books.springbootbooksrestapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Tag(
        name= "CRUD REST APIs for Categories of the Books"
)
public class CategoryController {
    private CategoryService categoryService;


     //Create Category REST API.
     //param categoryDto The category data provided in the request body.
     //return ResponseEntity containing the created CategoryDto and HTTP status 201 (CREATED).

    @Operation(
            summary = "Create Category REST API",
            description = "Creation of Category REST API in order to save category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){

        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }


     //Get Category By Id REST API.
     //param categoryId The ID of the category to retrieve.
     //return ResponseEntity containing the retrieved CategoryDto and HTTP status 200 (OK).
    @Operation(
            summary = "Get Category By Id REST API",
            description = "Get Category By Id REST API in order to get category by Id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> getCategoryById (@PathVariable(value = "id") Long categoryId) {

        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    //Get All Categories REST API.
    //return ResponseEntity containing the list of all categories and HTTP status 200 (OK).
    @Operation(
            summary = "Get All Categories REST API",
            description = "Get All the Categories REST API in order to get all Categories from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());

    }


     //Update Category REST API.
     //param categoryId  The ID of the category to update.
     //param categoryDto The updated category data provided in the request body.
     //return ResponseEntity containing the updated CategoryDto and HTTP status 200 (OK).
    @Operation(
            summary = "Update Category REST API",
            description = "Update Category REST API in order to update category into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                      @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.updateCategory(categoryId,categoryDto),HttpStatus.OK);
    }


     //Delete Category REST API.
     //param categoryId The ID of the category to delete.
     //return ResponseEntity with a success message and HTTP status 200 (OK).
    @Operation(
            summary = "Delete Category REST API",
            description = "Delete Category REST API in order to delete category into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted succesfully",HttpStatus.OK);
    }
}
