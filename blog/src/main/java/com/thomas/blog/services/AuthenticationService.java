package com.thomas.blog.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails); // potentially will take the argument("userDetails") from the above function
    public UserDetails validateToken(String token);
}
