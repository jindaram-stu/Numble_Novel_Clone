package com.numble.challenge.novel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.utility.nullability.NeverNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NovelEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @JsonIgnore
    @OneToMany(mappedBy = "novelEpisode", cascade = CascadeType.ALL)
    private List<NovelContent> contents;

    // 회차 제목
    private String title;
    
    // 회차 순서
    @Builder.Default
    private Long sequence = 1L;

    // 회차 별 조회수
    @Builder.Default
    private Long hit = 0L;

    // 가격
    private Long price;

    // 작성일
    @Builder.Default
    private Instant createDate = Instant.now();

    public void addContent(List<NovelContent> contents) {
        this.contents = contents;
        contents.stream()
                .forEach(content -> content.setEpisode(this));
    }

}
