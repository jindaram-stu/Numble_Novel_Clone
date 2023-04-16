package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.domain.Novel;
import com.numble.challenge.novel.domain.NovelEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NovelEpisodeRepository extends JpaRepository<NovelEpisode, Long> {

    @Query("SELECT ne FROM NovelEpisode ne " +
            "LEFT JOIN FETCH ne.novel nn " +
            "LEFT JOIN FETCH ne.contents nc " +
            "WHERE nn.id = :novelId AND ne.id = :episodeId")
    Optional<NovelEpisode> findByNovelEpisodeId(@Param("novelId") Long novelId,
                                                @Param("episodeId") Long episodeId);

    @Modifying
    @Query("UPDATE NovelEpisode ne SET ne.hit = ne.hit + :hit WHERE ne.id = :episodeId")
    void bulkHit(@Param("hit") Long hit,
                 @Param("episodeId") Long episodeId);

    Long countNovelEpisodeByNovel(Novel novel);

}
