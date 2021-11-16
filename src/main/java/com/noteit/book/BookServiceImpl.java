package com.noteit.book;

import com.noteit.dto.BookDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class BookServiceImpl implements BookService{


    @Resource
    private BookRepository bookRepository;

    @Resource
    private BookTransformer bookTransformer;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookTransformer.toBookDTOs(bookRepository.findAll());
    }
}
