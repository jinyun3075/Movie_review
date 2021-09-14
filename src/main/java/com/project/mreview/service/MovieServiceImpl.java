package com.project.mreview.service;

import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.movie.MovieRepository;
import com.project.mreview.entity.movieimage.MovieImage;
import com.project.mreview.entity.movieimage.MovieImageRepository;
import com.project.mreview.web.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDto movieDto) {
        Map<String,Object> entityMap = dtoToEntity(movieDto);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(i ->{
            imageRepository.save(i);
        });

        return movie.getMno();
    }
}
