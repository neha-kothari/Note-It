package com.noteit.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteit.dto.BookDTO;
import com.noteit.dto.BookDetailsDTO;
import com.noteit.dto.ChapterDTO;
import com.noteit.dto.FileDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    @Resource
    private BookService bookService;


    @ExceptionHandler(Exception.class)
    public BookDTO handleException(Exception e) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setError(e.getMessage());
        e.printStackTrace();
        return bookDTO;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(){

        List<BookDTO> bookDTOs = bookService.getAllBooks();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookDTOs);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDetailsDTO> getBookDetails(@PathVariable Long book_id){
        try {
            BookDetailsDTO bookDetailsDTO = bookService.getBookDetails(book_id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookDetailsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/split")
    public ResponseEntity<BookDetailsDTO> getBookDetails(@RequestBody BookDetailsDTO bookDetails){
        try {
            BookDetailsDTO bookDetailsDTO = bookService.splitBook(bookDetails);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookDetailsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("/{book_id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long book_id) {

        try {
            FileDTO file = bookService.downloadBook(book_id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\\"+file.getFileName()+"\\")
                    .body(file.getData());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
