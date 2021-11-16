package com.noteit.service;

import com.noteit.bean.User;
import com.noteit.pojo.UserRegistrationPOJO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationPOJO registrationPOJO);

    User getUserFromUserId(int id);

}