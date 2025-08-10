package com.myproject.ratingservice.mapper;

import com.myproject.commonlibs.mapper.EntityCreateUpdateMapper;
import com.myproject.ratingservice.model.Rating;
import com.myproject.ratingservice.view_model.rating.RatingPostVM;
import com.myproject.ratingservice.view_model.rating.RatingVM;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author nguyenle
 * @since 5:47 PM Sun 8/10/2025
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RatingMapper extends EntityCreateUpdateMapper<Rating, RatingPostVM, RatingVM> {
    @Override
    RatingVM toVmResponse(Rating rating);
}
