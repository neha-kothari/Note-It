package com.noteit.dto;

import com.noteit.book.Book;
import com.noteit.notebook.Notebook;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    @Column(nullable = false)
    private int userType;
    @Column(nullable = false)
    private char accountStatus;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "uploadedBy")
    private List<Book> uploadedBooks;

    @OneToMany(mappedBy = "userId")
    private List<Notebook> notebooks;

}
