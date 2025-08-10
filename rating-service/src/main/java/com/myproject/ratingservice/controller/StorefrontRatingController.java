package com.myproject.ratingservice.controller;

import com.myproject.commonlibs.constant.PagingAndSortingConstant;
import com.myproject.ratingservice.service.RatingService;
import com.myproject.ratingservice.view_model.rating.RatingPostVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author nguyenle
 * @since 5:28 PM Sun 8/10/2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/storefront/ratings")
public class StorefrontRatingController {

    private final RatingService ratingService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getRatingList(
            @PathVariable Long productId,
            @RequestParam(value = "pageIndex", defaultValue = PagingAndSortingConstant.DEFAULT_PAGE_NUMBER, required = false) int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = PagingAndSortingConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        return ResponseEntity.ok(ratingService.getRatingListByProductId(productId, pageIndex, pageSize));
    }

    @GetMapping("/product/{productId}/average-star")
    public ResponseEntity<?> getAverageStar(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(ratingService.calculateAverageRating(productId));
    }

    @PostMapping
    public ResponseEntity<?> createRating(
            @Valid @RequestBody RatingPostVM ratingPostVM
    ) {
        return ResponseEntity.ok(ratingService.createRating(ratingPostVM));
    }

}
