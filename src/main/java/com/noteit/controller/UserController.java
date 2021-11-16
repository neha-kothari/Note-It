package com.noteit.controller;

import com.noteit.bean.User;
import com.noteit.pojo.UserRegistrationPOJO;
import com.noteit.repository.UserRepository;
import com.noteit.service.UserService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

@RestController
@RequestMapping("/v1")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /*@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody User user) {
        log.info("UserResourceImpl : authenticate");
        JSONObject jsonObject = new JSONObject();
        try {
            log.info(user.getEmailAddress() + " : " + user.getPassword());
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
            if (authentication.isAuthenticated()) {
                String email = user.getEmailAddress();
                jsonObject.put("name", authentication.getName());
                jsonObject.put("token", tokenProvider.createToken(email));
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
            }else{
                jsonObject.put("data","Invalid Credentials");
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
            }
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }*/

    @ModelAttribute("user")
    public UserRegistrationPOJO userRegistrationDTO() {
        return new UserRegistrationPOJO();
    }

    @GetMapping("/user/profile")
    @ResponseBody
    public User getUserDetails(Authentication auth) {

        User user = userRepository.findByEmailAddress(auth.getName());
        user.setPassword("");
        return user;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public ResponseEntity registerUserAccount(@RequestBody UserRegistrationPOJO registrationPOJO) {
        try {
            userService.save(registrationPOJO);
            return ResponseEntity.status(HttpStatus.OK).body("{'data':'Registered Successfully'}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'data':'Email already in use'}");
//			return "redirect:/registration?success";
        }
    }


}