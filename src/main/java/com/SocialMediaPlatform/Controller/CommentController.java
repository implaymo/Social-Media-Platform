package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Mapper.CommentMapper;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
import com.SocialMediaPlatform.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;


    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/comment/{postID}")
    public ResponseEntity<Boolean> registerComment(@Valid @RequestBody CommentDto commentDto, @PathVariable String postID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            String userID = customUserDetails.getId();
            Comment commentMap = commentMapper.toEntity(commentDto);
            Optional<Comment> comment = commentService.registerComment(commentMap, postID, userID);
            if (comment.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(true);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}
