package com.noteit.book;

import com.noteit.dto.BookDTO;
import com.noteit.dto.BookDetailsDTO;
import com.noteit.dto.FileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;


public interface BookService {

    List<BookDTO> getAllBooks();

    BookDetailsDTO getBookDetails(Long bookId) throws Exception;
    byte[] retrieveBook(String bookPath) throws Exception;
    FileDTO downloadBook(Long bookId) throws Exception ;

    BookDTO addBook(BookDTO bookDetails, MultipartFile bookFile, Long user_id) throws Exception;
    String uploadBookFile(MultipartFile bookFile) throws Exception;
}
