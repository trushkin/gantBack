package com.example.ganttback.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
        //@CrossOrigin(origins = "http://localhost:3000")
    Long login(@RequestBody UserDto userDto) {
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getPassword());
        return userService.login(userDto);
    }
}
