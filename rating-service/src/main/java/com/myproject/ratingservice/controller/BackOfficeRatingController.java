package com.myproject.ratingservice.controller;

import com.myproject.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 5:34 PM Sun 8/10/2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice/ratings")
public class BackOfficeRatingController {

    private final RatingService ratingService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok(null);
    }
}
