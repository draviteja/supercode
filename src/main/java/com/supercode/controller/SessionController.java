package com.supercode.controller;

import com.supercode.payload.JwtAuthenticationResponse;
import com.supercode.payload.LoginRequest;
import com.supercode.security.JwtTokenProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@ApiModel(description="Session controller for login and logout")
@RestController
@RequestMapping("/api/auth")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Login user",
            notes = "return a jwt token for valid credentials")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {

        logger.info("user login initiated for user with name : " + loginRequest.getUsername());
        logger.info("user login initiated for user with pwd: " + loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                getAuthenticationObject(loginRequest)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = tokenProvider.generateToken(authentication);
        //response.setHeader("Token", jwtToken);
        Cookie sessionCookie = new Cookie( "token", jwtToken );
        response.addCookie( sessionCookie );
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
    }

    /*@PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }*/

    private Authentication getAuthenticationObject(LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
