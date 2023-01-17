package com.example.authservice.controller;

import com.example.authservice.dto.CreateUserDTO;
import com.example.authservice.dto.LoginResponseDTO;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.dto.UserTokenState;
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

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthentificationService authentificationService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> createAuthenticationToken(
            @Valid @RequestBody JwtAuthenticationRequest authenticationRequest) {

        LoginResponseDTO loginResponseDTO = authentificationService.login(authenticationRequest);
        if (loginResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody UserTokenState userTokenState) {
        // TODO
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@Valid @RequestBody CreateUserDTO userDTO) {
        User user = authentificationService.addUser(userDTO);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Sent verification email", HttpStatus.CREATED);
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
