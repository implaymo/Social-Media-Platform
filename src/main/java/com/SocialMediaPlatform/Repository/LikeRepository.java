package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
}
