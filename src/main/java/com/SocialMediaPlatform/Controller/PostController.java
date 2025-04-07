package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Mapper.PostMapper;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.Post.PostService;
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


    private final PostMapper postMapper;
    private final PostService postService;


    public PostController(PostMapper postMapper, PostService postService) {
        this.postMapper = postMapper;
        this.postService = postService;
    }

    @PostMapping(path = "/post/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createPost(@Valid @RequestBody PostDto postDto,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Post post = postMapper.toEntity(postDto);
        Optional<Post> postCreated = postService.createPost(post, customUserDetails);
        if(postCreated.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping(path = "/post/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePost(@Valid @RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Optional<Post> postUpdated = postService.updatePost(post);
        if(postUpdated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(path = "/post/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deletePost(@Valid @RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        Optional<Post> postDelete = postService.deletePost(post);
        if(postDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
