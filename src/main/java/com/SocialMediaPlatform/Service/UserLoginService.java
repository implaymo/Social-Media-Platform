package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserLoginDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserLoginMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserLoginService {

    private final UserRepository userRepository;
    private final UserLoginMapper userLoginMapper;

    public UserLoginService(UserRepository userRepository, UserLoginMapper userLoginMapper) {
        this.userRepository = userRepository;
        this.userLoginMapper = userLoginMapper;
    }


    @Transactional
    public String loginUser(UserLoginDto userLoginDto) {
        if (userLoginDto == null) {
            return null;
        }

        try {
            User user = userLoginMapper.toEntityForLogin(userLoginDto);
            if(userRepository.existsByEmail(user.getEmail())){
                Optional<User> userInDatabase = userRepository.findByEmail(user.getEmail());
                String userInDatabaseSalt = userInDatabase.get().getSalt();


            }
            return "dDSA890DSA9DS8A";
        } catch (Exception e) {
            return null;
        }
    }
}
