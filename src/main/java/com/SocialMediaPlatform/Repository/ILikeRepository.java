package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeRepository extends MongoRepository<Like, String> {
    Optional<Like> findByPostIDAndUserID(String postID, String userID);
}
