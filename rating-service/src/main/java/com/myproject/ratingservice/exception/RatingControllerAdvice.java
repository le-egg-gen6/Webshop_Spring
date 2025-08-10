package com.myproject.ratingservice.exception;

import com.myproject.commonlibs.exception.AbstractControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author nguyenle
 * @since 5:31 PM Sun 8/10/2025
 */
@Slf4j
@RestControllerAdvice
public class RatingControllerAdvice extends AbstractControllerAdvice {

    @Autowired
    public RatingControllerAdvice(MessageSource messageSource) {
        super(messageSource);
    }
}
