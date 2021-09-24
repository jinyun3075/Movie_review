package com.project.mreview.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewnum; // review num

    private Long mno; // Movie mno

    private Long mid; // Member id;

    private String nickname; // Member nickname

    private String email; // Member email

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;
}
