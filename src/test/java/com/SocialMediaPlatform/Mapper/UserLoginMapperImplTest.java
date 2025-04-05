package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginMapperImplTest {

    private UserLoginMapperImpl userLoginMapper;

    @BeforeEach
    void setUp() {
        userLoginMapper = new UserLoginMapperImpl();
    }


    @Test
    void shouldTransformUserLoginDtoIntoUser(){
        // arrange
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        User user = userLoginMapper.toEntityForLogin(userLoginDto);
        // assert
        assertNotNull(user);
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("John@12345", user.getPassword());
    }

    @Test
    void shouldReturnNullIfUserLoginDtoNull() {
        // act
        User user = userLoginMapper.toEntityForLogin(null);
        // assert
        assertNull(user);
    }

}