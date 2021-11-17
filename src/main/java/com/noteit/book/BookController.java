package com.noteit.book;

import com.noteit.dto.BookDTO;
import com.noteit.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Heereeeeeee");
        List<BookDTO> bookDTOs = bookService.getAllBooks();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookDTOs);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookDTO> getBookDetails(@PathVariable Long book_id){
        BookDTO bookDTO = bookService.getBookDetails(book_id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookDTO);
    }

    @GetMapping("/{book_id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long book_id){
        FileDTO file = bookService.downloadBook(book_id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\\"+file.getFileName()+"\\")
                .body(file.getData());
    }
}
