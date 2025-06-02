package com.paymedia.jwt_authentication.controller;

import com.paymedia.jwt_authentication.dto.LoginResponse;
import com.paymedia.jwt_authentication.dto.LoginUserDto;
import com.paymedia.jwt_authentication.dto.RegisterUserDto;
import com.paymedia.jwt_authentication.entity.user.Role;
import com.paymedia.jwt_authentication.entity.user.User;
import com.paymedia.jwt_authentication.repository.RoleRepository;
import com.paymedia.jwt_authentication.service.AuthenticationService;
import com.paymedia.jwt_authentication.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/auth")
@RestController
@Slf4j
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final RoleRepository roleRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService , RoleRepository roleRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        Role role = roleRepository.findByRoleName("USER");
        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(registerUserDto.getPassword());
        user.setRoles(Set.of(role));
        user.setFullName(registerUserDto.getFullName());
        log.info("Registering user: {}", user);
        User registeredUser = authenticationService.signup(user);

        return ResponseEntity.ok(registeredUser);
    }
   @PostMapping("/signup/officer")
    public ResponseEntity<User> officerRegister(@RequestBody RegisterUserDto registerUserDto) {
        Role role = roleRepository.findByRoleName("OFFICER");
        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(registerUserDto.getPassword());
        user.setRoles(Set.of(role));
        user.setFullName(registerUserDto.getFullName());
        log.info("Registering Officer: {}", user);
        User registeredUser = authenticationService.signup(user);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/signup/testuser")
    public ResponseEntity<User> TesterRegister(@RequestBody RegisterUserDto registerUserDto) {
        Role role = roleRepository.findByRoleName("TEST");
        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(registerUserDto.getPassword());
        user.setRoles(Set.of(role));
        user.setFullName(registerUserDto.getFullName());
        log.info("Registering Tester: {}", user);
        User registeredUser = authenticationService.signup(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}