package com.grazielleanaia.spring_security_jwt.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    @Mock
    private JwtDecoder jwtDecoder;

    @Mock
    private Claims claims;

    @Mock
    private Jwts jwts;

    @InjectMocks
    JwtUtil jwtUtil;
    String username;
    String token;


//    @BeforeEach
//    void setup() {
//       jwtUtil = new JwtUtil();
//    }

    @Test
    void generateToken_ReturnValidJwt() {
       long fixedTime = 153789700000L;
       Date fixedDate = new Date(fixedTime);
    }

    @Test
    void validateToken_validateTokenUsername_ReturnsTrue() {
//        String token = "valid-token";
        String username = "valid-username";
     when(claims.getSubject());

    }

    interface JwtDecodeToken{
        String decodeToken(String token) throws MalformedJwtException;
    }
}
