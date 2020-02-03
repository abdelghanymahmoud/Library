package com.library.services;

import com.library.dto.JwtResponse;
import com.library.dto.UserRequest;
import com.library.logger.LibraryLogger;
import com.library.models.Role;
import com.library.models.User;
import com.library.repositories.RoleRepository;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegistrationService {

    private final UserRepository userRepo;
    private final LoginService loginService;
    private final RoleRepository roleRepo;

    @Autowired
    public RegistrationService(UserRepository userRepo, LoginService loginService, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.loginService = loginService;
        this.roleRepo = roleRepo;
    }

    public JwtResponse saveUser(UserRequest userRequest) throws Exception {
        LibraryLogger.log.info("Enter saveUser method of RegistrationService class");
        isEmailExist(userRequest.getEmail());
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("REGULAR"));
        user.setRoles(roles);
        if (userRequest.getName() != null)
            user.setName(userRequest.getName());
        userRepo.save(user);
        LibraryLogger.log.info("Before return statement in saveUser method of RegistrationService class");
        return loginService.doLogin(userRequest);
    }

    private void isEmailExist(String email) {
        LibraryLogger.log.info("Enter isEmailExist method of TicketController class");
        if (userRepo.findByEmail(email) != null) {
            LibraryLogger.log.info("Before throws statement in isEmailExist method of RegistrationService class");
        }
        LibraryLogger.log.info("End of isEmailExist method of RegistrationService class without throws exception");
    }

}

