package com.noteit.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteit.book.BookService;
import com.noteit.dto.*;
import com.noteit.notebook.NotebookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @Resource
    private NotebookService notebookService;

    @PutMapping(path = "/login")
    public boolean getLoginStatus(@RequestBody UserDTO request) {
        //return userService.getLoginStatus(request);
        return false;
    }

    @PostMapping(path = "/register")
    public boolean registerUser(@RequestBody UserDTO request) {
        //return userService.getLoginStatus(request);
        return false;
    }

    @PostMapping(path ="/users/{user_id}/upload")
    public ResponseEntity<BookDetailsDTO> uploadBook(@RequestParam("json") String requestString, @RequestParam("file") MultipartFile bookFile, @PathVariable Long user_id) throws Exception {

        BookDetailsDTO request = new ObjectMapper().readValue(requestString, BookDetailsDTO.class);
        if (validateUploadRequest(request)) {
            return ResponseEntity.created(null)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(bookService.addBook(request, bookFile, user_id));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
        }
    }



    @PostMapping(path ="/users/{user_id}/notes")
    public ResponseEntity<NotebookDTO> saveNotes(@RequestBody NotebookDTO notebookDTO, @PathVariable Long user_id) throws Exception {

        notebookService.saveNotes(notebookDTO, user_id);
        return ResponseEntity.created(null)
                .contentType(MediaType.APPLICATION_JSON)
                .body(null);

    }

    @GetMapping(path ="/users/{user_id}/notes")
    public ResponseEntity<NotesOutputDTO> getNotes(@PathVariable Long user_id) throws Exception {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(notebookService.getNotes(user_id));

    }

    @GetMapping(path ="/users/{user_id}/profile")
    public ResponseEntity<Object> getProfile(@PathVariable Long user_id) throws Exception {

        UserDTO userDTO = userService.profile(user_id);
        if (null != userDTO) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.profile(user_id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID does not exist!");

    }

    @DeleteMapping(path ="/users/{user_id}/notes/{chapter_id}")
    public ResponseEntity<NotesOutputDTO> deleteFromNotes(@PathVariable Long user_id, @PathVariable Long chapter_id) throws Exception {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(notebookService.deleteChapter(user_id, chapter_id));

    }

    @PostMapping(path ="/users/{user_id}/notes/merge")
    public ResponseEntity<ByteArrayResource> mergeNotes(@RequestBody NotebookDTO notebookDTO, @PathVariable Long user_id) throws Exception {

        if (notebookDTO.getCustom_name() == null || notebookDTO.getCustom_name().isEmpty()) {
            throw new Exception("Invalid Notebook Name");
        }
        try {
            FileDTO file = notebookService.mergeNotes(notebookDTO, user_id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\\"+file.getFileName()+"\\")
                    .body(file.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping(path ="/users/trial")
    public ResponseEntity<User> addUser(@RequestBody UserRegistrationDTO user) throws Exception {

        return ResponseEntity.created(null)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.addUser(user));
    }

    private boolean validateUploadRequest(BookDetailsDTO request) {

        if (Strings.isBlank(request.getBookName())) {
            request.setError("Book Title should not be blank.");
            return false;
        } else if (Strings.isBlank(request.getAuthor())) {
            request.setError("Author should not be blank.");
            return false;
        } else if (Strings.isBlank(request.getDescription())) {
            request.setError("Description should not be blank.");
            return false;
        } else if (Strings.isBlank(request.getIsbnNumber())) {
            request.setError("ISBN should not be blank.");
            return false;
        } else if (bookService.isbnExists(request.getIsbnNumber())) {
            request.setError("Book with this ISBN number exists already.");
            return false;
        }
        return true;
    }
}
