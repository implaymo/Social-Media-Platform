package com.SocialMediaPlatform.Service;

import com.SocialMediaPlatform.Dto.UserRegisterDto;
import com.SocialMediaPlatform.Entity.User;
import com.SocialMediaPlatform.Mapper.UserRegisterMapper;
import com.SocialMediaPlatform.Repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;

public class UserService {

    UserRepository userRepository;
    UserRegisterMapper userRegisterMapper;

    public UserService(UserRepository userRepository, UserRegisterMapper userRegisterMapper) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
    }

    public boolean registerUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            return false;
        }

        try {
            User user = userRegisterMapper.toEntity(userRegisterDto);
            userRepository.save(user);
            return true;
        } catch (ConstraintViolationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
