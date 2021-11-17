package com.noteit.book;

import com.noteit.dto.BookDTO;
import com.noteit.dto.FileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;


public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookDetails(Long bookId);

    FileDTO downloadBook(Long bookId);

    BookDTO addBook(BookDTO bookDetails, MultipartFile bookFile, Long user_id) throws Exception;
    Book uploadBook(Book book, MultipartFile imageFile) throws Exception;
}
