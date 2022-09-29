package com.ssafy.chuanione.domain.review.service;

import com.ssafy.chuanione.domain.review.dto.ReviewRequestDto;
import com.ssafy.chuanione.domain.review.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    List<ReviewResponseDto> getList(int id);
    ReviewResponseDto insertReview(ReviewRequestDto dto, int id);
    ReviewResponseDto updateReview(ReviewRequestDto dto, int id);
    void deleteReview(int id);


}
