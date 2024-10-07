package com.adil.social.controller;


import com.adil.social.config.JwtProvider;
import com.adil.social.exceptions.UserException;
import com.adil.social.models.User;
import com.adil.social.repository.UserRepository;
import com.adil.social.request.LoginRequest;
import com.adil.social.response.AuthResponse;
import com.adil.social.service.CustomUserDetailsService;
import com.adil.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetails;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws UserException {

        User isExist = userRepository.findByEmail(user.getEmail());

        //if user already exist with the provied email
        if(isExist != null){
            throw new UserException("Email already in use by another account!");
        }

        User newUser = new User();


        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setGender(user.getGender());

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Registered Success!");

        return res;
    }


    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Login Success!");

        return res;
    }
    //

    private Authentication authenticate(String email, String password){
        UserDetails userDetails = customUserDetails.loadUserByUsername(email);


        if(userDetails == null){
            throw new BadCredentialsException("Invalid username!");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Password not matched!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
