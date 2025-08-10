package com.myproject.ratingservice.view_model.rating;

import lombok.*;

import java.util.Date;

/**
 * @author nguyenle
 * @since 5:39 PM Sun 8/10/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingVM {

    private Long id;

    private String content;

    private int star;

    private Long productId;

    private String productName;

    private String createdBy;

    private String firstName;

    private String lastName;

    private Date createdAt;
}
