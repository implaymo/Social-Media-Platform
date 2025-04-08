package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Interface.Post.IPostMapper;
import com.SocialMediaPlatform.Interface.Post.IPostService;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
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


    private final IPostMapper iPostMapper;
    private final IPostService iPostService;


    public PostController(IPostMapper iPostMapper, IPostService iPostService) {

        this.iPostMapper = iPostMapper;
        this.iPostService = iPostService;
    }

    @PostMapping(path = "/post/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createPost(@Valid @RequestBody PostDto postDto,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Post post = iPostMapper.toEntity(postDto);
        Optional<Post> postCreated = iPostService.createPost(post, customUserDetails);
        if(postCreated.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping(path = "/post/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updatePost(@Valid @RequestBody PostDto postDto) {
        Post post = iPostMapper.toEntity(postDto);
        Optional<Post> postUpdated = iPostService.updatePost(post);
        if(postUpdated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(path = "/post/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deletePost(@Valid @RequestBody PostDto postDto) {
        Post post = iPostMapper.toEntity(postDto);
        Optional<Post> postDelete = iPostService.deletePost(post);
        if(postDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
