package com.numble.challenge.novel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "novel_id")
    private Long id;


    @OneToMany(mappedBy = "novel")
    private List<NovelEpisode> episodes;

    private String title;

    private String author;

    private String description;

    @Enumerated(EnumType.STRING)
    private FreeType freeType;

    @Enumerated(EnumType.STRING)
    private Genre genre;

}
