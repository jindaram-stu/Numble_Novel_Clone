package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.domain.FavoriteNovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteNovelRepository extends JpaRepository<FavoriteNovel, Long> {

    @Query("SELECT f FROM FavoriteNovel f " +
           "JOIN FETCH f.novel n " +
           "WHERE f.user.id = :id")
    List<FavoriteNovel> findByUserId(@Param("id") Long id);

}
