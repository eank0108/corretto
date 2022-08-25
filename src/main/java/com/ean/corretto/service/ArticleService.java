package com.ean.corretto.service;

import com.ean.corretto.dto.ArticleResponseDto;
import com.ean.corretto.dto.RequestDto;
import com.ean.corretto.exception.CustomException;
import com.ean.corretto.exception.ErrorCode;
import com.ean.corretto.model.Article;
import com.ean.corretto.model.Attachment;
import com.ean.corretto.model.Board;
import com.ean.corretto.repository.ArticleRepository;
import com.ean.corretto.repository.ArticleSpecification;
import com.ean.corretto.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;
    @Transactional
    //게시글 생성
    public void createArticle(RequestDto.Create requestDto) {
        //게시판 존재여부 확인
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_BOARD));

        // 임시 Attachment 생성
        Attachment mockAttachment1 = Attachment.builder().location("mock location1").build();
        Attachment mockAttachment2 = Attachment.builder().location("mock location2").build();
        Attachment mockAttachment3 = Attachment.builder().location("mock location3").build();

        Article article = Article.builder()
                .board(board)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .attachments(Arrays.asList(mockAttachment1,mockAttachment2,mockAttachment3))
                .viewCount(0)
                .build();
        articleRepository.save(article);
    }

    //게시글 목록
    public List<ArticleResponseDto> getArticles(Timestamp startDateTime, Timestamp endDateTime, String boardName) {

        return articleRepository.findAll(new ArticleSpecification(startDateTime, endDateTime, boardName)).stream().map(article ->
                ArticleResponseDto.builder()
                        .boardName(article.getBoard().getName())
                        .title(article.getTitle())
                        .created_datetime(article.getCreatedDatetime())
                        .location(article.getAttachments().get(0).getLocation())
                        .build()
        ).collect(Collectors.toList());
    }

    //게시글 상세
    public ArticleResponseDto getArticle(Integer articleId) {
        //게시글 존재여부 확인
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ARTICLE));

        return ArticleResponseDto.builder()
                .boardName(article.getBoard().getName())
                .title(article.getTitle())
                .created_datetime(article.getCreatedDatetime())
                .locations(article.getAttachments().stream().map(Attachment::getLocation).collect(Collectors.toList()))
                .build();
    }

    //게시글 삭제
    @Transactional
    public void deleteArticle(Integer articleId) {
        //게시글 존재여부 확인
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ARTICLE));
        articleRepository.delete(article);
    }

    //게시글 수정
    @Transactional
    public void updateArticle(Integer articleId,RequestDto.Update requestDto) {
        //게시글 존재여부 확인
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_ARTICLE));
        article.update(requestDto.getTitle(), requestDto.getContent());

    }
}
