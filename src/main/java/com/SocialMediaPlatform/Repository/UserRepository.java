package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean passwordMatch(String password);
    Optional<User> findByEmail(String email);

}
