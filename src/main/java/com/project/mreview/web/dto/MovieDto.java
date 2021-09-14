package com.project.mreview.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long mno;

    private String title;

    @Builder.Default
    private List<MovieImageDto> imageDtoList= new ArrayList<>();

}
