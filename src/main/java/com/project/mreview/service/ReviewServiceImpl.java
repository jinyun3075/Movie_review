package com.project.mreview.service;

import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.review.Review;
import com.project.mreview.entity.review.ReviewRepository;
import com.project.mreview.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview->entityToDto(movieReview)).collect(Collectors.toList());

    }

    @Override
    public Long register(ReviewDto movieReviewDto) {
        Review movieReview = dtoToEntity(movieReviewDto);

        reviewRepository.save(movieReview);

        return movieReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDto movieReviewDto) {
        Optional<Review> result = reviewRepository.findById(movieReviewDto.getReviewnum());

        if(result.isPresent()){
            Review movieReview = result.get();
            movieReview.changeGrade(movieReviewDto.getGrade());
            movieReview.changeText(movieReviewDto.getText());

            reviewRepository.save(movieReview);
        }
    }

    @Override
    public void remove(Long reviewnum) {
        reviewRepository.deleteById(reviewnum);
    }
}
