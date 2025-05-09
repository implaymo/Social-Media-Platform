package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Config.TestAuthenticationPrincipalArgumentResolver;
import com.SocialMediaPlatform.Domain.Like;
import com.SocialMediaPlatform.Domain.Post;
import com.SocialMediaPlatform.Interface.Like.ILikeService;
import com.SocialMediaPlatform.Repository.IPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LikeControllerTest {

    private MockMvc mockMvc;
    private String userID;
    private Post post;
    private Like like;

    @Mock
    private ILikeService iLikeService;

    @Mock
    private IPostRepository postRepository;

    @InjectMocks
    private LikeController likeController;

    @BeforeEach
    void setUp(){
        userID = "userLikeID";
        mockMvc = MockMvcBuilders.standaloneSetup(likeController)
                .setCustomArgumentResolvers(new TestAuthenticationPrincipalArgumentResolver(userID))
                .build();
        post = Post.builder()
                .postId("postID")
                .content("comment of post")
                .mediaUrl("example.jpg")
                .userId("userCreatorPostID")
                .build();

        like = Like.builder()
                .postID(post.getPostId())
                .userID(userID)
                .build();
    }

    @Test
    void shouldReturnCreateIfRegisterLike() throws Exception {
        // arrange
        when(postRepository.findById(post.getPostId())).thenReturn(Optional.of(post));
        when(iLikeService.registerLike(post.getPostId(), userID)).thenReturn(Optional.of(like));
        // act
        // assert
        mockMvc.perform(post("/like/" + post.getPostId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("true"));
    }

    @Test
    void shouldReturnConflictIfPostIDNotExists() throws Exception {
        // arrange
        String falsePostID = "falsePostID";
        // act
        // assert
        mockMvc.perform(post("/like/" + falsePostID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("false"));
    }

    @Test
    void shouldReturnConflictIfPostIDNull() throws Exception {
        // arrange
        // act
        // assert
        mockMvc.perform(post("/like/" + null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("false"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLikeServiceIsNull() throws Exception {
        // arrange
        IPostRepository iPostRepository = mock(IPostRepository.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
           new LikeController(null, iPostRepository);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfPostRepositoryIsNull() throws Exception {
        // arrange
        ILikeService iLikeService = mock(ILikeService.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new LikeController(iLikeService, null);
        });
    }
}