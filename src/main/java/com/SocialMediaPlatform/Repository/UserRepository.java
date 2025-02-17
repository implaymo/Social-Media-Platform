package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    Optional<User> findByEmail(String email);

}
