package com.noteit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {

    private Long bookId;
    private String bookName;
    private String isbnNumber;
    private String author;
    private String publisher;
    private Integer yearOfRelease;
    private String imageLocation;
    private String description;
    private UserDTO uploadedByUser;
    private boolean isDeleted;
    private String error;
}
