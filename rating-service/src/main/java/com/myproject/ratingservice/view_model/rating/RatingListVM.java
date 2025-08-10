package com.myproject.ratingservice.view_model.rating;

import com.myproject.commonlibs.view_model.AbstractPagableObject;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 5:48 PM Sun 8/10/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingListVM extends AbstractPagableObject {
    private List<RatingVM> ratings;
}
