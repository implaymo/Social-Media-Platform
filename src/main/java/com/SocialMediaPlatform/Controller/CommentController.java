package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.CommentDto;
import com.SocialMediaPlatform.Domain.Comment;
import com.SocialMediaPlatform.Interface.Comment.ICommentMapper;
import com.SocialMediaPlatform.Interface.Comment.ICommentService;
import com.SocialMediaPlatform.Security.CustomUserDetails.CustomUserDetails;
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

    private final ICommentService iCommentService;
    private final ICommentMapper iCommentMapper;


    public CommentController(ICommentService iCommentService, ICommentMapper iCommentMapper) {
        if(iCommentMapper == null || iCommentService == null) {
            throw new IllegalArgumentException("ICommentMapper or ICommentService is null");
        }
        this.iCommentService = iCommentService;
        this.iCommentMapper = iCommentMapper;
    }

    @PostMapping("/comment/{postID}")
    public ResponseEntity<Boolean> registerComment(@Valid @RequestBody CommentDto commentDto, @PathVariable String postID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            String userID = customUserDetails.getId();
            Comment commentMap = iCommentMapper.toEntity(commentDto);
            Optional<Comment> comment = iCommentService.registerComment(commentMap, postID, userID);
            if (comment.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(true);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}
