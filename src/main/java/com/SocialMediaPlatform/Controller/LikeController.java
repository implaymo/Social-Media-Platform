package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Domain.Like;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Repository.IPostRepository;
import com.SocialMediaPlatform.Service.Like.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LikeController {

    private final LikeService likeService;
    private final IPostRepository postRepository;

    public LikeController(LikeService likeService, IPostRepository postRepository) {
        this.postRepository = postRepository;
        this.likeService = likeService;
    }


    @PostMapping(path = "/like/{postID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> registerLike(@PathVariable String postID,
                                                @AuthenticationPrincipal User user){
        try {
            Optional<Post> postFound = postRepository.findById(postID);
            if(postFound.isPresent()) {
                String userID = user.getId();
                Optional<Like> likeRegistered = likeService.registerLike(postID, userID);
                if (likeRegistered.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(true);
                }
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
    }
}
