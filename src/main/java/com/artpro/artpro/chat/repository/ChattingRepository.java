package com.artpro.artpro.chat.repository;

import com.artpro.artpro.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingRepository extends JpaRepository<Message, Long> {
}
