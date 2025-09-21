package lk.ijse.backend.service;

import lk.ijse.backend.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
    int saveReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getAllReviewsByShopId(long shopId);
    List<ReviewDTO> getAllReviewsByServiceId(long serviceId);
}
