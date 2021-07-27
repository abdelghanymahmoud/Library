package com.library.repositories;

import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

// comment added here in the repo layer
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
