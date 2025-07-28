package com.myproject.ratingservice.repository;

import com.myproject.ratingservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 4:11 PM Sat 7/26/2025
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
