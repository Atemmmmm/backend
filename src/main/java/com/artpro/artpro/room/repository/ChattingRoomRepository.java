package com.artpro.artpro.room.repository;

import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    List<ChattingRoom> findAllByCreateByOrBoard_Member(Member createBy, Member boardMember);
    Optional<List<ChattingRoom>> findAllByBoard_Id(Long boardId);
}
