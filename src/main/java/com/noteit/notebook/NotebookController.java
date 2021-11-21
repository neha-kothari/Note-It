package com.noteit.notebook;

import com.noteit.book.BookService;
import com.noteit.dto.BookDetailsDTO;
import com.noteit.dto.NotebookDTO;
import com.noteit.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/notes")
public class NotebookController {

    @Resource
    private NotebookService notebookService;


    /*@PostMapping("/save")
    public ResponseEntity saveNotes(NotebookDTO notes){
        try {
            notebookService.saveNotes(notes, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }*/
}
