package com.artpro.artpro.room.service;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.room.dto.response.RoomResponse;
import com.artpro.artpro.room.repository.ChattingRoomRepository;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.entity.ChattingRoom;
import com.artpro.artpro.room.mapper.ChattingRoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final BoardRepository boardRepository;
    private final ChattingRoomMapper chattingRoomMapper;

    public void create(Member member, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        ChattingRoom room = chattingRoomMapper.mapToEntity(member, board);
        chattingRoomRepository.save(room);
    }

    public List<RoomResponse> findByMemberId(Member member) {
        List<ChattingRoom> rooms = chattingRoomRepository.findAllByCreateByOrBoard_Member(member, member);
        return rooms.stream()
                .map(room -> chattingRoomMapper.mapToRoomDto(room, member))
                .toList();
    }
}
