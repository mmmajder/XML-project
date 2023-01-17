package com.example.authservice.service;

import com.example.authservice.dto.CreateUserDTO;
import com.example.authservice.dto.JwtAuthenticationRequest;
import com.example.authservice.dto.LoginResponseDTO;
import com.example.authservice.dto.UserTokenState;
import com.example.authservice.model.User;
import com.example.authservice.model.UserAuth;
import com.example.authservice.utils.TokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Service
public class AuthentificationService {
    private final UserService userService;
    private final TokenUtils tokenUtils;
    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GradjaninService gradjaninService;
    private final SluzbenikService sluzbenikService;

    public User addUser(CreateUserDTO createUserDTO) {
        return userService.findByEmail(createUserDTO.getEmail()) == null ? saveUser(createUserDTO) : null;
    }

    private User saveUser(CreateUserDTO createUserDTO) {
        User user = DTOMapper.getUser(createUserDTO);
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setUserAuth(getUserAuth(user));
        switch (createUserDTO.getUserRole()) {
            case GRADJANIN -> gradjaninService.saveUser(user);
            case SLUZBENIK -> sluzbenikService.saveUser(user);
        }
        return user;
    }

    private UserAuth getUserAuth(User user) {
        UserAuth userAuth = new UserAuth();
//        String randomCode = RandomString.make(64);
//        userAuth.setVerificationCode(RandomString.make(64));
//        userAuth.setLastPasswordSet(new Timestamp(System.currentTimeMillis()));
//        userAuth.setRoles(getRoles(user));
        userAuthService.save(userAuth);
        return userAuth;
    }

    public LoginResponseDTO login(JwtAuthenticationRequest authenticationRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            return null;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        return createAccessToken(user);
    }

    private LoginResponseDTO saveAuthInContext(User user) {
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtils.generateToken((User) authentication.getPrincipal());
        long expiresIn = tokenUtils.getExpiredIn();
        return new LoginResponseDTO(new UserTokenState(token, expiresIn), user.getRole());
    }

    private LoginResponseDTO createAccessToken(User user) {
        String jwt = tokenUtils.generateToken(user);
        long expiresIn = tokenUtils.getExpiredIn();
        return new LoginResponseDTO(new UserTokenState(jwt, expiresIn), user.getRole());
    }

}
