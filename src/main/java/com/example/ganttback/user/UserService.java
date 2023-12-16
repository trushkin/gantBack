package com.example.ganttback.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser() {
        userRepository.save(new User("vlad.trukhan@gmail.com", passwordEncoder.encode("trukhan123")));
    }

    public Long login(UserDto userDto) {
        System.out.println(userDto.getEmail());
//        Long t = userRepository.findByEmailAndPassword(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword())).get().getId();
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(userDto.getEmail()).get();
            if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
                return user.getId();
            }
            return -1L;
        }
        return -1L;
    }
}
