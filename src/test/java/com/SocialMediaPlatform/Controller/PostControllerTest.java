package com.SocialMediaPlatform.Controller;

import com.SocialMediaPlatform.Dto.PostDto;
import com.SocialMediaPlatform.Entity.Post;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private User user;


    @BeforeEach
    void setUp(){
        user = User.builder()
                .id("userID")
                .name("John Doe")
                .email("john@example.com")
                .password("password")
                .build();
    }


    // createPost method
    @Test
    void shouldReturnCreatedIfPostCreatedSuccessfully() {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .userId("userID")
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), any(User.class)))
                .thenReturn(Optional.of(post));

        // act
        ResponseEntity<Boolean> response = postController.createPost(postDto, user);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnOkIfPostCreatedWithContent() {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .build();
        when(postService.createPost(any(PostDto.class), eq(user)))
                .thenReturn(Optional.of(post));

        // act
        ResponseEntity<Boolean> response = postController.createPost(postDto, user);
        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnOkIfPostCreatedOnlyWithMedia() {
        // arrange
        PostDto postDto = PostDto.builder()
                .mediaUrl("example.mp4")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), eq(user)))
                .thenReturn(Optional.of(post));
        // act
        ResponseEntity<Boolean> response = postController.createPost(postDto, user);
        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnFalseIfDtoIsNull() {
        // arrange
        PostDto postDto = null;
        // act
        ResponseEntity<Boolean> response = postController.createPost(postDto, user);
        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody());

    }

    @Test
    void shouldReturnInternalServerErrorForUnhandledException() {
        // arrange
        PostDto postDto = PostDto.builder()
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), eq(user)))
                .thenThrow(new RuntimeException("Unexpected Error"));
        // act + assert
        assertThrows(RuntimeException.class, () -> postController.createPost(postDto, user));
    }

    @Test
    void shouldReturnBadRequestIfPostAlreadyExistsInDatabase() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello world")
                .mediaUrl("example.mp4")
                .build();
        when(postService.createPost(any(PostDto.class), eq(user)))
                .thenReturn(Optional.empty());

        // act
        ResponseEntity<Boolean> response = postController.createPost(postDto, user);
        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody());
    }

    //////////////////updatePost Test //////////////

    @Test
    void shouldReturnOkIfUpdatePostContentAndMedia() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("exampleNewMedia.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act
        ResponseEntity<Boolean> response = postController.updatePost(postDto);
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnOkIfUpdatePostContentOnly() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .mediaUrl("example.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act
        ResponseEntity<Boolean> response = postController.updatePost(postDto);
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnOkIfUpdatePostContentOnlyAndNoMedia(){
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("New content")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act
        ResponseEntity<Boolean> response = postController.updatePost(postDto);
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());

    }

    @Test
    void shouldReturnOkIfUpdatePostMediaOnly() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        Post updatedPost = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("exampleIsKing.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.of(updatedPost));

        // act
        ResponseEntity<Boolean> response = postController.updatePost(postDto);
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnBadRequestIfNotUpdatePost() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.updatePost(any(PostDto.class))).thenReturn(Optional.empty());

        // act
        ResponseEntity<Boolean> response = postController.updatePost(postDto);
        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody());

    }


    //////////////////////////// deletePost tests ///////////////////////////////////////////

    @Test
    void shouldReturnOkIfDeletePost() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        Post post = Post.builder()
                .postId("postID")
                .content("Hello World")
                .mediaUrl("example.jpg")
                .build();
        when(postService.deletePost(any(PostDto.class))).thenReturn(Optional.of(post));
        // act
        ResponseEntity<Boolean> response = postController.deletePost(postDto);
        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    void shouldReturnBadRequestIfPostNotExist() {
        // arrange
        PostDto postDto = PostDto.builder()
                .postId("postID")
                .mediaUrl("example.jpg")
                .build();
        when(postService.deletePost(any(PostDto.class))).thenReturn(Optional.empty());
        // act
        ResponseEntity<Boolean> response = postController.deletePost(postDto);
        // assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody());
    }
}