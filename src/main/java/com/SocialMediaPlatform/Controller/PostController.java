package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/post/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createPost(@Valid @RequestBody PostDto postDto,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Optional<Post> post = postService.createPost(postDto, customUserDetails);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping(path = "/post/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePost(@Valid @RequestBody PostDto postDto) {
        Optional<Post> post = postService.updatePost(postDto);
        if(post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(path = "/post/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deletePost(@Valid @RequestBody PostDto postDto) {
        Optional<Post> post = postService.deletePost(postDto);
        if(post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
