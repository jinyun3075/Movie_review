package com.project.mreview.controller;

import com.project.mreview.service.ReviewService;
import com.project.mreview.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDto>> getList(@PathVariable("mno") Long mno){
        log.info("-------------list------------");
        log.info("MNO:"+mno);

        List<ReviewDto> reviewDtoList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDtoList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDto dto){
        log.info("-------------add MovieReview----------");
        log.info("reviewDto:" + dto);

        Long revienum = reviewService.register(dto);

        return new ResponseEntity<>(revienum,HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody
                                             ReviewDto movieReviewDto){
        log.info("--------------modify MovieReview--------------");
        log.info("reviewDto :"+movieReviewDto);

        reviewService.modify(movieReviewDto);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){
        log.info("--------------modify removeReview--------------");
        log.info("reviewnum :"+reviewnum);

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);

    }

}
