package com.project.mreview.entity.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    //coalesce : 둘중 하나가 null 경우 null이 아닌값 출력 , distinct : 중복 제거
    @Query("SELECT m, mi, avg(coalesce(r.grade,0)), count(distinct r) " +
            "from Movie m " +
            "left outer join MovieImage mi on mi.movie = m "+
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) "+
            "from Movie m left outer join MovieImage mi on mi.movie=m "+
            "left outer join Review r on r.movie=m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);
}
