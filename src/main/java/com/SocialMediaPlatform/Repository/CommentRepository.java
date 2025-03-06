package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.Comment;
import com.SocialMediaPlatform.Entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

}
