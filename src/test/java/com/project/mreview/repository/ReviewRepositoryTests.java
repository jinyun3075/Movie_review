package com.project.mreview.repository;

import com.project.mreview.entity.member.Member;
import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.review.Review;
import com.project.mreview.entity.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {
    @Autowired
    ReviewRepository repository;

    @Test
    public void insertReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            //영화 번호
            Long mno = (long) (Math.random() * 100) + 1;
            //멤버 번호
            Long mid = (long) (Math.random() * 100) + 1;

            Member member = Member.builder().mid(mid).build();
            Movie movie = Movie.builder().mno(mno).build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int) (Math.random()*5)+1)
                    .text("영화의 느낌 "+i+"번째")
                    .build();

            repository.save(review);
        });
    }
    @Test
    public void testGetMovieReviews(){
        Movie movie = Movie.builder()
                .mno(92L)
                .build();

        List<Review> result = repository.findByMovie(movie);

        result.forEach(i->{
            System.out.print(i.getReviewnum());
            System.out.print("\t"+i.getGrade());
            System.out.print("\t"+i.getText());
            System.out.print("\t"+i.getMember().getEmail());
            System.out.println("-----------------");
        });
    }
}
