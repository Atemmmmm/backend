package com.artpro.artpro.heart.repository;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<List<Heart>> findHeartByBoardAndMember(Board board, Member member);
}
