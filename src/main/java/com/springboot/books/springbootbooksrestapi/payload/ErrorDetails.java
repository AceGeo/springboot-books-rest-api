package com.springboot.books.springbootbooksrestapi.payload;

import lombok.Getter;

import java.util.Date;

//A class representing details of an error response.
@Getter
public class ErrorDetails {


    //The timestamp when the error occurred.

    private final Date timestamp;


     // The error message.

    private final String message;


    // Additional details about the error.
    private final String details;


     //Constructs an instance of ErrorDetails.

     //@param timestamp The timestamp when the error occurred.
     //@param message   The error message.
     //@param details   Additional details about the error.
    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
