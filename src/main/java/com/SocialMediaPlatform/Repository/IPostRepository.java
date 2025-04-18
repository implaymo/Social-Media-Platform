package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends MongoRepository<Post, String> {
}
