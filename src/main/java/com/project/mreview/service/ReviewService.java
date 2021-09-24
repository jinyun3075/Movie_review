package com.project.mreview.service;

import com.project.mreview.entity.member.Member;
import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.review.Review;
import com.project.mreview.web.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getListOfMovie(Long mno);

    Long register(ReviewDto movieReviewDto);

    void modify(ReviewDto movieReviewDto);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDto reviewDto){
        Review movieReview = Review.builder()
                .reviewnum(reviewDto.getReviewnum())
                .movie(Movie.builder().mno(reviewDto.getMno()).build())
                .member(Member.builder().mid(reviewDto.getMid()).build())
                .grade(reviewDto.getGrade())
                .text(reviewDto.getText())
                .build();
        return movieReview;
    }

    default ReviewDto entityToDto(Review review){
        ReviewDto reviewDto = ReviewDto.builder()
                .reviewnum(review.getReviewnum())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .nickname(review.getMember().getNickname())
                .email(review.getMember().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();
        return reviewDto;
    }
}
