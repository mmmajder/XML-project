package com.example.authservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class AuthentificationService {
    private final UserService userService;
    private final AdminService adminService;
    private final TokenUtils tokenUtils;
    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User addUser(CreateUserDTO createUserDTO) throws MessagingException {
        if (userService.findByEmail(createUserDTO.getEmail()) != null) {
            return null;
        }
        User user = saveUser(createUserDTO);
        if (user.getRole() == UserRole.CUSTOMER) {
            emailService.sendRegistrationAsync(user);
        }
        return user;
    }

    private User saveUser(CreateUserDTO createUserDTO) {
        User user = DTOMapper.getUser(createUserDTO);
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setBlocked(false);
        user.setUserAuth(getUserAuth(user));
        switch (createUserDTO.getUserRole()) {
            case CUSTOMER -> customerService.createCustomer((Customer) user);
            case ADMIN -> adminService.save(user);
            case DRIVER -> driverService.save(user);
        }
        return user;
    }

    private UserAuth getUserAuth(User user) {
        UserAuth userAuth = new UserAuth();
        String randomCode = RandomString.make(64);
        userAuth.setVerificationCode(randomCode);
        userAuth.setLastPasswordSet(new Timestamp(System.currentTimeMillis()));
        userAuth.setRoles(getRoles(user));
        userAuth.setIsEnabled(setIsUserEnabledRegistration(user));
        userAuthService.save(userAuth);
        return userAuth;
    }

    private boolean setIsUserEnabledRegistration(User user) {
        return user.getRole() != UserRole.CUSTOMER;
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
        if (!isUserEnabled(user)) return null;
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
