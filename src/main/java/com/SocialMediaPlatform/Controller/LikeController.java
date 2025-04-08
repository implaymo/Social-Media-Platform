package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Domain.Like;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Domain.User;
import com.SocialMediaPlatform.Interface.Like.ILikeService;
import com.SocialMediaPlatform.Repository.IPostRepository;
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

    private final ILikeService iLikeService;
    private final IPostRepository iPostRepository;

    public LikeController(ILikeService iLikeService, IPostRepository iPostRepository) {
        if(iLikeService == null || iPostRepository == null) {
            throw new IllegalArgumentException("likeService or postRepository is null");
        }
        this.iLikeService = iLikeService;
        this.iPostRepository = iPostRepository;
    }


    @PostMapping(path = "/like/{postID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> registerLike(@PathVariable String postID,
                                                @AuthenticationPrincipal User user){
        try {
            Optional<Post> postFound = iPostRepository.findById(postID);
            if(postFound.isPresent()) {
                String userID = user.getId();
                Optional<Like> likeRegistered = iLikeService.registerLike(postID, userID);
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
