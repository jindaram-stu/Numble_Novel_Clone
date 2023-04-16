package com.numble.challenge.novel.repository;

import com.numble.challenge.novel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
           "LEFT JOIN FETCH u.favoriteNovels f " +
           "WHERE u.email = :email")
    Optional<User> findByEmail(String email);

}
