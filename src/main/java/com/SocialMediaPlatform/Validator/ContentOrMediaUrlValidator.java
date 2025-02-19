package com.SocialMediaPlatform.Validator;

import com.SocialMediaPlatform.CustomAnnotation.ContentOrMediaUrlRequired;
import com.SocialMediaPlatform.Dto.PostDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContentOrMediaUrlValidator implements ConstraintValidator<ContentOrMediaUrlRequired, PostDto> {

    @Override
    public boolean isValid(PostDto postDto, ConstraintValidatorContext context) {
        return postDto.getContent() != null && !postDto.getContent().isEmpty() ||
                postDto.getMediaUrl() != null && !postDto.getMediaUrl().isEmpty();
    }
}