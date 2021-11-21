package com.noteit.user;

import com.noteit.dto.UserRegistrationDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User addUser(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setName(userDTO.getFirstName() + " " + userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user = userRepository.save(user);
        return user;
    }
}
