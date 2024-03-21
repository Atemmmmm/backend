package com.artpro.artpro.chat.repository;

import com.artpro.artpro.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChattingRoom_Id(Long roomId);
}
