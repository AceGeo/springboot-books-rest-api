package com.springboot.books.springbootbooksrestapi.exception;

import org.springframework.http.HttpStatus;

//Custom exception class for representing exceptions in the Blog API.
public class BlogAPIException extends RuntimeException {

    //HTTP status associated with the exception.
    private HttpStatus status;

    //Detailed message providing information about the exception.
    private  String message;

     //Constructor for creating an instance of BlogAPIException.
     //@param status  HTTP status associated with the exception.
     //@param message Detailed message providing information about the exception.
    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


     //Constructor for creating an instance of BlogAPIException with a cause.

     //@param message Detailed message providing information about the exception.
     //@param status  HTTP status associated with the exception.
     //@param message1 Detailed message providing information about the exception cause.

    public BlogAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    //Retrieves the HTTP status associated with the exception.
    public HttpStatus getStatus() {
        return status;
    }

    //Overrides the getMessage method to provide the detailed message
    @Override
    public String getMessage() {
        return message;
    }
}
