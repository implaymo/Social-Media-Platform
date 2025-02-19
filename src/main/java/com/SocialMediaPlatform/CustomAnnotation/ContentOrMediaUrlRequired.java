package com.SocialMediaPlatform.CustomAnnotation;

import com.SocialMediaPlatform.Validator.ContentOrMediaUrlValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContentOrMediaUrlValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentOrMediaUrlRequired {
    String message() default "Either content or mediaUrl must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}