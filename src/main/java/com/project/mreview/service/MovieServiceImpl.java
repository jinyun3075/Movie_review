package com.project.mreview.service;

import com.project.mreview.entity.movie.Movie;
import com.project.mreview.entity.movie.MovieRepository;
import com.project.mreview.entity.movieimage.MovieImage;
import com.project.mreview.entity.movieimage.MovieImageRepository;
import com.project.mreview.web.dto.MovieDto;
import com.project.mreview.web.dto.PageRequestDto;
import com.project.mreview.web.dto.PageResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[],MovieDto> fn = (arr->entitiesToDto(
                (Movie)arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        );

        return new PageResultDto<>(result,fn);
    }
}
