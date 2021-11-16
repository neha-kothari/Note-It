package com.noteit.user;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
}
