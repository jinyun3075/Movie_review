package com.project.mreview.service;

import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.movieimage.MovieImage;
import com.project.mreview.web.dto.MovieDto;
import com.project.mreview.web.dto.MovieImageDto;
import com.project.mreview.web.dto.PageRequestDto;
import com.project.mreview.web.dto.PageResultDto;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDto movieDto);

    MovieDto getMovie(Long mno);

    default Map<String,Object> dtoToEntity(MovieDto movieDto){
        Map<String,Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDto.getMno())
                .title(movieDto.getTitle())
                .build();

        entityMap.put("movie",movie);

        List<MovieImageDto> imageDtoList = movieDto.getImageDtoList();

        if(imageDtoList != null && imageDtoList.size()>0){
            List<MovieImage> movieImagesList =imageDtoList.stream()
                    .map(movieImageDto -> {
                        MovieImage movieImage = MovieImage.builder()
                                .path(movieImageDto.getPath())
                                .imgName(movieImageDto.getImgName())
                                .uuid(movieImageDto.getUuid())
                                .movie(movie)
                                .build();
                        return movieImage;
                    }).collect(Collectors.toList());
            entityMap.put("imgList",movieImagesList);
        }
        return entityMap;

    }

    PageResultDto<MovieDto,Object[]> getList(PageRequestDto requestDto);
    default MovieDto entitiesToDto(Movie movie, List<MovieImage> movieImages
    ,double avg, Long reviewCnt){
        MovieDto movieDto = MovieDto.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        List<MovieImageDto> movieImageDtoList = movieImages.stream()
                .map(i->{
                    return MovieImageDto.builder()
                            .imgName(i.getImgName())
                            .path(i.getPath())
                            .uuid(i.getUuid())
                            .build();
                }).collect(Collectors.toList());

        movieDto.setImageDtoList(movieImageDtoList);
        movieDto.setAvg(avg);
        movieDto.setReviewCnt(reviewCnt.intValue());

        return movieDto;
    }
}
