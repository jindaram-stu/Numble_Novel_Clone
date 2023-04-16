package com.numble.challenge.novel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovelContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    private NovelEpisode novelEpisode;

    // 페이지
    private Long page;

    // 소설 내용
    @Lob
    private String content;

    public void setEpisode(NovelEpisode novelEpisode) {
        this.novelEpisode = novelEpisode;
    }

}
