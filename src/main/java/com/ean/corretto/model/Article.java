package com.ean.corretto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(length = 128, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column
    @ColumnDefault("0")
    private Integer viewCount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private List<Attachment> attachments;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
