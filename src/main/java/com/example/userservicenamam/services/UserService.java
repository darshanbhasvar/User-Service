package com.example.userservicenamam.services;


import com.example.userservicenamam.models.User;
import com.example.userservicenamam.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User signup(String name,
                       String email,
                       String password){
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setHashedPassword(passwordEncoder.encode(password));
        User user = userRepository.save(u);
        return user;
    }
}
