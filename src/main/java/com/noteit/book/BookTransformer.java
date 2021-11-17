package com.noteit.book;

import com.noteit.dto.BookDTO;
import com.noteit.user.User;
import com.noteit.user.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class BookTransformer {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private UserRepository userRepository;

    public List<BookDTO> toBookDTOs(List<Book> books) {
        if (Objects.isNull(books)) {
            return new ArrayList<>();
        }

        List<BookDTO> bookDTOs = new ArrayList<>();
        books.forEach(book -> {
            if (Objects.nonNull(book)) {
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookId(book.getBookId());
                bookDTO.setBookName(book.getBookName());
                bookDTO.setIsbnNumber(book.getIsbnNumber());
                bookDTO.setAuthor(book.getAuthor());
                bookDTO.setPublisher(book.getPublisher());
                bookDTO.setYearOfRelease(book.getYearOfRelease());
                bookDTO.setDescription(book.getDescription());
                bookDTO.setImageLocation(book.getImageLocation());
                bookDTO.setUploadedByUser("Neha");
                //bookDTO.setUploadedByUser(userRepository.findByUserId(book.getUploadedBy().getUserId()).getName());
                bookDTO.setDeleted(book.isDeleted());

                bookDTOs.add(bookDTO);
            } else {
                bookDTOs.add(null);
            }
        });

        return bookDTOs;
    }

    public Book toEntity(Book book, BookDTO request) {
        book.setBookName(request.getBookName());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setIsbnNumber(request.getIsbnNumber());

        //User user = userRepository.findById(request.getUploadedByUser());

       /* book.setGraduationYear(LocalDate.of(Integer.parseInt(request.getGraduationYear()), 1, 1));
        book.setTotalCredits(request.getTotalCredits());
        book.setDeleted(request.isDeleted());

        Domain domain = domainRepository.findByProgram(request.getDomainDto().getProgram());
        student.setDomain(domain);

        Specialisation specialisation = specialisationRepository.findByCode(request.getSpecialisationDto().getCode());
        student.setSpecialisation(specialisation);*/

        return bookRepository.saveAndFlush(book);
    }
}
