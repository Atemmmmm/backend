package com.artpro.artpro.room.service;

import com.artpro.artpro.board.entity.Board;
import com.artpro.artpro.board.exception.BoardNotFoundException;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.chat.entity.Type;
import com.artpro.artpro.chat.repository.ChattingRepository;
import com.artpro.artpro.room.dto.response.RoomResponse;
import com.artpro.artpro.room.repository.ChattingRoomRepository;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.room.entity.ChattingRoom;
import com.artpro.artpro.room.mapper.ChattingRoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final BoardRepository boardRepository;
    private final ChattingRepository chattingRepository;
    private final ChattingRoomMapper chattingRoomMapper;

    public void create(Member member, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
        ChattingRoom room = createChattingRoom(member, board);
        createFirstMessage(room, board);
    }

    private ChattingRoom createChattingRoom(Member member, Board board) {
        ChattingRoom room = chattingRoomMapper.mapToEntity(member, board);
        chattingRoomRepository.save(room);
        return room;
    }

    private void createFirstMessage(ChattingRoom room, Board board) {
        Message message = Message.builder()
                .chattingRoom(room)
                .createAt(String.valueOf(LocalDateTime.now()))
                .type(Type.MUSIC)
                .content(String.valueOf(board.getId()))
                .sender(room.getCreateBy().getEmail())
                .build();
        chattingRepository.save(message);
    }

    public List<RoomResponse> findByMemberId(Member member) {
        List<ChattingRoom> rooms = chattingRoomRepository.findAllByCreateByOrBoard_Member(member, member);
        return rooms.stream()
                .map(room -> {
                    Message message = chattingRepository.findFirstByChattingRoomOrderByCreateAtDesc(room);
                    Member counterpart = room.findCounterpart(member);
                    return chattingRoomMapper.mapToRoomDto(room, counterpart, message);
                })
                .toList();
    }
}
