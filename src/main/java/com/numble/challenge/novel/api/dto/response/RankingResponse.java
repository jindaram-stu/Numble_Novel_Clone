package com.numble.challenge.novel.api.dto.response;


import com.numble.challenge.novel.domain.Genre;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingResponse {

    private Long id;

    private String title;
    
    private String author;
    
    private String description;

    private Genre genre;

    private Long totalHits;

}
