package com.noteit.book;

import com.noteit.chapter.Chapter;
import com.noteit.chapter.ChapterService;
import com.noteit.dto.BookDTO;
import com.noteit.dto.BookDetailsDTO;
import com.noteit.dto.ChapterDTO;
import com.noteit.dto.FileDTO;
import com.noteit.user.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    @Value(("${noteit.book.path}"))
    private String bookBasePath;

    @Value(("${noteit.book.default-image.path}"))
    private String defaultImagePath;

    @Resource
    private BookRepository bookRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BookTransformer bookTransformer;

    @Resource
    private ChapterService chapterService;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookTransformer.toBookDTOs(bookRepository.findAll());
    }


    @Override
    @Transactional(readOnly = true)
    public BookDetailsDTO getBookDetails(Long bookId) throws Exception {

        Book book = bookRepository.findByBookId(bookId);
        return bookTransformer.toBookDetailsDTO(book);
    }

    @Override
    public BookDTO addBook(BookDTO bookDetails, MultipartFile bookFile, Long user_id) throws Exception{
        Book book = bookRepository.save(bookTransformer.toEntity(new Book(), bookDetails));
        if (book.getImageLocation() == null || book.getImageLocation().isEmpty()) {
            book.setImageLocation(defaultImagePath);
        }
        book.setFileLocation(uploadBookFile(bookFile));
        book.setUploadedBy(userRepository.findByUserId(user_id));
        book.setCreatedOn(LocalDateTime.now());
        bookRepository.save(book);

        List<Book> books = new ArrayList<>();
        books.add(bookTransformer.toEntity(book, bookDetails));
        return bookTransformer.toBookDTOs(books).get(0);

    }

    @Override
    @Transactional
    public String uploadBookFile(MultipartFile bookFile) throws Exception {
        byte[] bytes = bookFile.getBytes();
        String imagePath = System.currentTimeMillis() + bookFile.getOriginalFilename();
        Path path = Paths.get(bookBasePath + imagePath);
        Files.write(path, bytes);
        return bookBasePath + imagePath;
    }

    @Override
    @Transactional(readOnly = true)
    public FileDTO downloadBook(Long bookId) throws Exception {
        Book book = bookRepository.findByBookId(bookId);
        FileDTO file = new FileDTO();
        file.setFileName(book.getBookName());
        file.setData(new ByteArrayResource(retrieveBook(book.getFileLocation())));
        return file;
    }

    @Override
    public byte[] retrieveBook(String bookPath) throws Exception {
        if (Strings.isBlank(bookPath)) {
            return null;
        }
        Path path = Paths.get(bookPath);
        return Files.readAllBytes(path);
    }

    @Override
    @Transactional
    public BookDetailsDTO splitBook(BookDetailsDTO bookDetails) throws Exception {

        Book book = bookRepository.findByBookId(bookDetails.getBookId());
        validateRequest(book, bookDetails.getChapters());
        List<Chapter> bookChapters = new ArrayList<>();
        for (ChapterDTO chapter : bookDetails.getChapters()) {
            Chapter c = chapterService.addChapter(chapter, book);
            bookChapters.add(c);
        }
        book.setChapters(bookChapters);
        book.setSplit(true);
        bookRepository.save(book);
        return bookTransformer.toBookDetailsDTO(book);
    }

    public void validateRequest(Book book, List<ChapterDTO> chapters) throws Exception{

    }
}

