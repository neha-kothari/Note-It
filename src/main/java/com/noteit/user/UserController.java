package com.noteit.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteit.book.BookService;
import com.noteit.dto.BookDTO;
import com.noteit.dto.UserDTO;
import com.noteit.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<BookDTO> uploadBook(@RequestParam("json") String requestString, @RequestParam("file") MultipartFile bookFile, @PathVariable Long user_id) throws Exception {

        BookDTO request = new ObjectMapper().readValue(requestString, BookDTO.class);
        return ResponseEntity.created(null)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.addBook(request, bookFile, user_id));

    }

    @PostMapping(path ="/users/trial")
    public ResponseEntity<User> addUser(@RequestBody UserRegistrationDTO user) throws Exception {

        return ResponseEntity.created(null)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.addUser(user));



    }
}
