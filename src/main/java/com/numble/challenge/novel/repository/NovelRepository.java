package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.api.dto.response.RankingResponse;
import com.numble.challenge.novel.domain.FreeType;
import com.numble.challenge.novel.domain.Novel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {

    @Query("SELECT n FROM Novel n " +
           "LEFT JOIN FETCH n.episodes e " +
           "WHERE e.id = :id ")
    Novel findNovelWithContent(@Param("id") Long id);

    @Query("SELECT new com.numble.challenge.novel.api.dto.response.RankingResponse(n.id, n.title, n.author, n.description, n.genre, sum(e.hit)) from Novel n " +
           "LEFT JOIN n.episodes e " +
            "WHERE n.freeType = :freeType " +
            "GROUP BY n.id")
    List<RankingResponse> findByFreeTypeOrderByHit(@Param("freeType") FreeType freeType, Pageable pageable);

    @Query("SELECT new com.numble.challenge.novel.api.dto.response.RankingResponse(n.id, n.title, n.author, n.description, n.genre, sum(e.hit)) from Novel n " +
           "LEFT JOIN n.episodes e " +
           "group by n.id")
    List<RankingResponse> findByAllOrderByHit(Pageable pageable);

}
