package com.library.services;

import com.library.dto.JwtResponse;
import com.library.dto.UserRequest;
import com.library.logger.LibraryLogger;
import com.library.security.JwtTokenUtil;
import com.library.security.JwtUserDetails;
import com.library.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                        JwtUserDetailsService jwtUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public JwtResponse doLogin(UserRequest request) {
        LibraryLogger.log.info("Enter doLogin method of LoginService class");
        final Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        LibraryLogger.log.info("Before return statement in doLogin method of LoginService class");
        return getJwtResponse(request, auth);
    }

    private JwtResponse getJwtResponse(UserRequest request, Authentication auth) {
        LibraryLogger.log.info("Enter getJwtResponse method of LoginService class");
        SecurityContextHolder.getContext().setAuthentication(auth);
        JwtUserDetails user = (JwtUserDetails) jwtUserDetailsService.loadUserByUsername(request.getEmail());
        if (!jwtUserDetailsService.isUserVerified(request.getEmail())) {
            return null;
        }
        String token = jwtTokenUtil.addAuthentication(user);
        LibraryLogger.log.info("Before return statement in getJwtResponse method of LoginService class");
        return new JwtResponse(token);
    }

}
