package com.example.userservicenamam.controllers;


import com.example.userservicenamam.dtos.LoginRequestDto;
import com.example.userservicenamam.dtos.LogoutRequestDto;
import com.example.userservicenamam.dtos.SignUpRequestDto;
import com.example.userservicenamam.models.User;
import com.example.userservicenamam.repository.TokenRepository;
import com.example.userservicenamam.repository.UserRepository;
import com.example.userservicenamam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;
    private UserService userService;

    public UserController(UserRepository userRepository, TokenRepository tokenRepository, UserService userService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }
    @GetMapping("/login")
    public User login(){
        // check if email and password in db
        // if yes return user
        // else throw some error
        return null;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequestDto request){
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        String email = request.getEmail();
        String name = request.getName();
        String passeord = request.getPassword();


        return userService.signup(name,email,passeord);
    }
    public ResponseEntity<Void> logout(){
        // delete token if exists -> 200
        // if doesn't exist give a 404
        return null;
    }
}
