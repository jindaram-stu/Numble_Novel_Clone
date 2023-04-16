package com.numble.challenge.novel.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "nb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<FavoriteNovel> favoriteNovels;

    @Builder.Default
    private Long point = 0L;

    public boolean isFavorite(Long novelId) {
        return favoriteNovels.stream()
                .anyMatch(novel -> novel.getNovel().getId().equals(novelId));
    }

    public void chargePoint(Long point) {
        this.point += point;
    }

    public void payment(Long point) {
        this.point -= point;
    }

}
