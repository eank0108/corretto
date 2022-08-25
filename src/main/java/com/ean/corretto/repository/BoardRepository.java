package com.ean.corretto.repository;

import com.ean.corretto.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
