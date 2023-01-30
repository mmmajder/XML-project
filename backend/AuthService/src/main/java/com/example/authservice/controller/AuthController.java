package com.example.authservice.controller;

import com.example.authservice.dto.*;
import com.example.authservice.model.User;
import com.example.authservice.service.AuthentificationService;
import com.example.authservice.service.DTOMapper;
import com.example.authservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthentificationService authentificationService;
    private final UserService userService;

    @PostMapping(path="/login", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<LoginResponseDTO> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) {

        LoginResponseDTO loginResponseDTO = authentificationService.login(authenticationRequest);
        if (loginResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody UserTokenState userTokenState) {
        // TODO
    }

    @PostMapping(path="/register", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<String> addUser(@RequestBody CreateUserDTO userDTO) {
        User user = authentificationService.addUser(userDTO);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/currently-logged-user")
    public ResponseEntity<UserResponse> loggedUser(Authentication authentication) {
        User user = userService.getLoggedUser(authentication);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserResponse dto = DTOMapper.getUserResponse(user);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
