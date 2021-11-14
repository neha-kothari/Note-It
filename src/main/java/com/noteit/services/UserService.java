package com.noteit.services;

import com.noteit.beans.User;
import com.noteit.beans.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDto);

    User getUserFromUserId(int id);

}