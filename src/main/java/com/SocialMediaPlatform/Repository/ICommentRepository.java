package com.SocialMediaPlatform.Repository;

import com.SocialMediaPlatform.Entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends MongoRepository<Comment, String> {

}
