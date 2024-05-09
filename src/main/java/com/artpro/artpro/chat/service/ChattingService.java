package com.artpro.artpro.chat.service;

import com.artpro.artpro.chat.dto.ChattingListResponse;
import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.chat.exception.DoesNotExistMemberException;
import com.artpro.artpro.chat.mapper.MessageMapper;
import com.artpro.artpro.chat.repository.ChattingRepository;
import com.artpro.artpro.file.repository.FileRepository;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.exception.MemberNotFoundException;
import com.artpro.artpro.member.repository.MemberRepository;
import com.artpro.artpro.room.entity.ChattingRoom;
import com.artpro.artpro.room.exception.DoesNotExistRoomException;
import com.artpro.artpro.room.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final MessageMapper messageMapper;

    public void createMessage(MessageRequest request, Long roomId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(DoesNotExistRoomException::new);
        Message message = messageMapper.toEntity(request, chattingRoom);
        chattingRepository.save(message);
    }

    public ChattingListResponse findAllByRoomId(Long roomId, Member member) {
        ChattingRoom room = chattingRoomRepository.findById(roomId)
                .orElseThrow(DoesNotExistRoomException::new);
        validateMember(room, member.getEmail());
        Member counterpart = findCounterpart(room, member.getId());
        List<Message> messages = chattingRepository.findAllByChattingRoom_Id(roomId).stream()
                .toList();
        return messageMapper.toDto(messages, counterpart);
    }

    private void validateMember(ChattingRoom room, String senderEmail) {
        if (!room.isParticipants(senderEmail)) {
            throw new DoesNotExistMemberException();
        }
    }

    private Member findCounterpart(ChattingRoom chattingRoom, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        return chattingRoom.findCounterpart(member);
    }

    public String saveFile(MultipartFile file) {
        return fileRepository.save(file);
    }
}
