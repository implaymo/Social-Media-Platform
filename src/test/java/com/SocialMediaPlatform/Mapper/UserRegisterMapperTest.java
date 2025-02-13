package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserRegisterMapperTest {

    private UserRegisterMapper userRegisterMapper;

    @BeforeEach
    void setUp() {
        userRegisterMapper = new UserRegisterMapper();
    }

    @Test
    void shouldReturnValidTransformation() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        User user = userRegisterMapper.toEntity(userRegisterDto);
        // assert
        assertNotNull(user);
        assertEquals("John Cena", user.getName());
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("John@12345", user.getPassword());
    }

    @Test
    void shouldHandleNullInput() {
        // act
        User user = userRegisterMapper.toEntity(null);
        // assert
        assertNull(user);
    }
}