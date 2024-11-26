package com.ayadsec.tp1sec.userRepo;

import com.ayadsec.tp1sec.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
