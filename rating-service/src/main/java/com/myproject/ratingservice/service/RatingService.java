package com.myproject.ratingservice.service;

import com.myproject.ratingservice.repository.RatingRepository;
import com.myproject.ratingservice.view_model.rating.RatingListVM;
import com.myproject.ratingservice.view_model.rating.RatingPostVM;
import com.myproject.ratingservice.view_model.rating.RatingVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:38 PM Sat 7/26/2025
 */
public interface RatingService {

    RatingListVM getRatingListByProductId(Long productId, int pageIndex, int pageSize);

    RatingVM createRating(RatingPostVM ratingVM);

    void deleteRating(Long id);

    Double calculateAverageRating(Long productId);

}
