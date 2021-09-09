package com.project.mreview.entity.movieimage;

import com.project.mreview.entity.movie.Movie;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String uuid;

    private String imgName;

    @ManyToOne
    private Movie movie;
}
