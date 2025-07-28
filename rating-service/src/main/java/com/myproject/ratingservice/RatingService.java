package com.myproject.ratingservice;

import com.myproject.ratingservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:38 PM Sat 7/26/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {

	private final RatingRepository ratingRepository;

}
