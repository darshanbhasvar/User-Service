package com.example.userservicenamam.controllers;


import com.example.userservicenamam.dtos.LoginRequestDto;
import com.example.userservicenamam.dtos.LogoutRequestDto;
import com.example.userservicenamam.dtos.SignUpRequestDto;
import com.example.userservicenamam.dtos.UserDto;
import com.example.userservicenamam.models.Token;
import com.example.userservicenamam.models.User;
import com.example.userservicenamam.repository.TokenRepository;
import com.example.userservicenamam.repository.UserRepository;
import com.example.userservicenamam.services.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto){

        return userService.login(requestDto.getEmail(), requestDto.getPassword());
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequestDto request){
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        return userService.signup(name,email,password);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        // delete token if exists -> 200
        // if doesn't exist give a 404
        userService.logout(requestDto.getToken());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable @NonNull String token){
        return UserDto.from(userService.validateToken(token));
    }
}
