package com.project.mreview.service;

import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.movieimage.MovieImage;
import com.project.mreview.web.dto.MovieDto;
import com.project.mreview.web.dto.MovieImageDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDto movieDto);

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
}
