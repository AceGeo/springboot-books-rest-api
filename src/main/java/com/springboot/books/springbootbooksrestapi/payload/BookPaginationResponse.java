package com.springboot.books.springbootbooksrestapi.payload;

import com.springboot.books.springbootbooksrestapi.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


 //A class representing a paginated response for books.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPaginationResponse {


        //The list of BookDto objects in the current page.
        private List<BookDto> content;


         //The page number.
        private int pageNo;

        //The number of books to include per page.
        private int pageSize;

        //The total number of elements across all pages.
        private long totalElements;


         //The total number of pages.
        private int totalPages;


         //A flag indicating whether this is the last page.
        private boolean last;
}
