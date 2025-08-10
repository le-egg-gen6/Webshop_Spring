package com.myproject.ratingservice.repository;

import com.myproject.ratingservice.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 4:11 PM Sat 7/26/2025
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Page<Rating> findByProductId(Long productId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.productId = :productId")
    Long getTotalRating(@Param("productId") Long productId);

    @Query("SELECT COALESCE(SUM(r.ratingStar), 0) FROM Rating r WHERE r.productId = :productId")
    Long getTotalStars(@Param("productId") Long productId);
}
