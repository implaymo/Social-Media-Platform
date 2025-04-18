package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    Optional<User> findByEmail(String email);

}
