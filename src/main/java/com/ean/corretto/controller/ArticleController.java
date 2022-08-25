package com.ean.corretto.controller;

import com.ean.corretto.dto.ArticleResponseDto;
import com.ean.corretto.dto.RequestDto;
import com.ean.corretto.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    //게시글 생성
    @PostMapping("/article")
    public String createArticle(@Valid @RequestBody RequestDto.Create requestDto){
        articleService.createArticle(requestDto);
        return "생성 성공";
    }

    //생성날짜, 게시판 이름 으로 게시글 가져오기
    @GetMapping("/article")
    public List<ArticleResponseDto> getArticles(
            @RequestParam(required = false) Timestamp startDateTime, //시작날짜
            @RequestParam(required = false) Timestamp endDateTime, //끝날짜
            @RequestParam(required = false) String boardName //게시판 이름
    ){
        return articleService.getArticles(startDateTime, endDateTime, boardName);
    }
    //게시글 상세
    @GetMapping("/article/{articleId}")
    public ArticleResponseDto getArticle(@PathVariable Integer articleId){
        return articleService.getArticle(articleId);
    }
    //게시글 삭제
    @DeleteMapping("/article/{articleId}")
    public String deleteArticle(@PathVariable Integer articleId){
        articleService.deleteArticle(articleId);
        return "삭제 성공";
    }

    //게시글 수정
    @PutMapping("/article/{articleId}")
    public String updateArticle(@PathVariable Integer articleId, @Valid @RequestBody RequestDto.Update requestDto){
        articleService.updateArticle(articleId, requestDto);
        return "수정 성공";
    }

}
