package com.noteit.book;

import com.noteit.dto.BookDTO;
import com.noteit.dto.FileDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {


    @Resource
    private BookRepository bookRepository;

    @Resource
    private BookTransformer bookTransformer;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookTransformer.toBookDTOs(bookRepository.findAll());
    }


    @Override
    @Transactional(readOnly = true)
    public BookDTO getBookDetails(Long bookId) {

        List<Book> books = new ArrayList<>();
        books.add(bookRepository.findByBookId(bookId));
        return bookTransformer.toBookDTOs(books).get(0);
    }

    @Override
    @Transactional
    public Book uploadBook(Book book, MultipartFile imageFile) throws Exception {
        byte[] bytes = imageFile.getBytes();
        book.setFile(bytes);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public FileDTO downloadBook(Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        FileDTO file = new FileDTO();
        file.setFileName(book.getBookName());
        file.setData(new ByteArrayResource((book.getFile())));
        return file;
    }

    @Override
    public BookDTO addBook(BookDTO bookDetails, MultipartFile bookFile, Long user_id) throws Exception{
        Book book = bookRepository.save(bookTransformer.toEntity(new Book(), bookDetails));
        uploadBook(book, bookFile);
        bookRepository.save(book);

        List<Book> books = new ArrayList<>();
        books.add(bookTransformer.toEntity(book, bookDetails));
        return bookTransformer.toBookDTOs(books).get(0);

    }
}

