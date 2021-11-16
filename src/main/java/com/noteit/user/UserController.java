package com.noteit.user;
import com.noteit.dto.UserDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PutMapping(path = "/login")
    public boolean getLoginStatus(@RequestBody UserDTO request) {
        //return userService.getLoginStatus(request);
        return false;
    }
}
