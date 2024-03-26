package com.artpro.artpro.board.repository;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByCategoryAndGenre(Pageable pageable, String category, Genre genre);
    Page<Board> findAllByMemberId(Pageable pageable, Long memberId);
}
