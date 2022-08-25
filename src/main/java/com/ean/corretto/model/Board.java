package com.ean.corretto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(length = 32, nullable = false)
    private String name;
}
