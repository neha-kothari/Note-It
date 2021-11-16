package com.noteit.book;

import com.noteit.dto.BookDTO;

import java.util.*;

public class BookTransformer {

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
                /*bookDTO.setEmail(student.getEmail());
                bookDTO.setFirstName(student.getFirstName());
                bookDTO.setLastName(student.getLastName());
                bookDTO.setGraduationYear(String.valueOf(student.getGraduationYear().getYear()));
                bookDTO.setTotalCredits(student.getTotalCredits());
                bookDTO.setDeleted(book.isDeleted());
                try {
                    studentDto.setImagePath(studentService.retrieveImage(student.getPhotographPath()));
                } catch (Exception e) {
                    bookDTO.setError(e.getMessage());
                }

                DomainDto domainDto = domainTransformer.toDto(student.getDomain());
                studentDto.setDomainDto(domainDto);

                SpecialisationDto specialisationDto = specialisationTransformer.toDto(student.getSpecialisation());
                studentDto.setSpecialisationDto(specialisationDto);*/

                bookDTOs.add(bookDTO);
            } else {
                bookDTOs.add(null);
            }
        });

        return bookDTOs;
    }
}
