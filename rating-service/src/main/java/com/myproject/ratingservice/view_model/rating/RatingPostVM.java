package com.myproject.ratingservice.view_model.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author nguyenle
 * @since 5:38 PM Sun 8/10/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingPostVM {

    @Size(max = 500)
    private String content;

    @Min(value = 0)
    @Max(value = 5)
    private int star;

    private Long productId;

    private String productName;

}
