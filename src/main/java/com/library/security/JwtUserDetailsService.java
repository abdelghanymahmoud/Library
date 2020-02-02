package com.library.security;

import com.library.mobels.User;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    private final UserRepository userRepo;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));

        }
        return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(),
                user.getRoles().iterator().next().getName());
    }

    public boolean isUserVerified(String username) {
        User user = userRepo.findByEmail(username);
        return user != null;
    }


}