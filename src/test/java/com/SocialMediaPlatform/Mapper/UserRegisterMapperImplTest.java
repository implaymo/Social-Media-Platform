package com.SocialMediaPlatform.Mapper;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserRegisterMapperImplTest {

    private UserRegisterMapperImpl userRegisterMapper;

    @BeforeEach
    void setUp() {
        userRegisterMapper = new UserRegisterMapperImpl();
    }


    // From UserRegisterDto to User Tests
    @Test
    void shouldReturnValidTransformation() {
        // arrange
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .name("John Cena")
                .email("john123@gmail.com")
                .password("John@12345")
                .build();
        // act
        User user = userRegisterMapper.toEntityForRegistration(userRegisterDto);
        // assert
        assertNotNull(user);
        assertEquals("John Cena", user.getName());
        assertEquals("john123@gmail.com", user.getEmail());
        assertEquals("John@12345", user.getPassword());
    }

    @Test
    void shouldHandleNullInput() {
        // act
        User user = userRegisterMapper.toEntityForRegistration(null);
        // assert
        assertNull(user);
    }

}