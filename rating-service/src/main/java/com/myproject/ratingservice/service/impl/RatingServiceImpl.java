package com.myproject.ratingservice.service.impl;

import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.ratingservice.exception.ExceptionConstant;
import com.myproject.ratingservice.mapper.RatingMapper;
import com.myproject.ratingservice.model.Rating;
import com.myproject.ratingservice.repository.RatingRepository;
import com.myproject.ratingservice.service.RatingService;
import com.myproject.ratingservice.view_model.rating.RatingListVM;
import com.myproject.ratingservice.view_model.rating.RatingPostVM;
import com.myproject.ratingservice.view_model.rating.RatingVM;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nguyenle
 * @since 5:26 PM Sun 8/10/2025
 */
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private static final String DEFAULT_RATING_SORT_FIELD = "createdAt";

    private static final Sort.Direction DEFAULT_RATING_SORT_DIRECTION = Sort.Direction.DESC;

    private final RatingRepository ratingRepository;

    private final RatingMapper ratingMapper;

    @Override
    public RatingListVM getRatingListByProductId(Long productId, int pageIndex, int pageSize) {
        Sort sort = Sort.by(DEFAULT_RATING_SORT_DIRECTION, DEFAULT_RATING_SORT_FIELD);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Rating> ratings = ratingRepository.findByProductId(productId, pageable);
        List<Rating> ratingList = ratings.getContent();

        RatingListVM ratingListVM = new RatingListVM();
        ratingListVM.applyPageableMetadata(ratings);

        ratingListVM.setRatings(ratingList.stream().map(ratingMapper::toVmResponse).toList());

        return ratingListVM;
    }

    @Override
    public RatingVM createRating(RatingPostVM ratingVM) {
        return null;
    }

    @Override
    public void deleteRating(Long id) {
        boolean isRatingExist = ratingRepository.existsById(id);
        if (!isRatingExist) {
            throw new NotFoundException(ExceptionConstant.RATING_NOT_FOUND, id);
        }
        ratingRepository.deleteById(id);
    }

    @Override
    public Double calculateAverageRating(Long productId) {
        Long totalRating = ratingRepository.getTotalRating(productId);
        Long totalStars = ratingRepository.getTotalStars(productId);
        if (totalRating == null || totalStars == null || totalRating <= 0 || totalStars <= 0) {
            return 0.0;
        }
        return (double) totalStars / totalRating;
    }
}
