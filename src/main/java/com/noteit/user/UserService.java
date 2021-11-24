package com.noteit.user;

import com.noteit.dto.UserDTO;
import com.noteit.dto.UserRegistrationDTO;

public interface UserService {

    User addUser(UserRegistrationDTO userDTO);
    UserDTO profile(Long user_id);
}
