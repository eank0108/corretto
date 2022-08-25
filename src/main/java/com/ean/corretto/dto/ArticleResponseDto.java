package com.ean.corretto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleResponseDto {
    private String boardName;
    private String title;
    private Timestamp created_datetime;
    private String location;
    private List<String> locations;
}
