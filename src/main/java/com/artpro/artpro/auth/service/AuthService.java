package com.artpro.artpro.auth.service;

import com.artpro.artpro.auth.dto.EmailRequest;
import com.artpro.artpro.auth.dto.NicknameRequest;
import com.artpro.artpro.auth.dto.PasswordRequest;
import com.artpro.artpro.auth.dto.RegisterRequest;
import com.artpro.artpro.auth.exception.ExistingEmailException;
import com.artpro.artpro.auth.exception.MismatchPasswordException;
import com.artpro.artpro.auth.mapper.AuthMapper;
import com.artpro.artpro.auth.repository.AuthRepository;
import com.artpro.artpro.member.constant.ResponseMessage;
import com.artpro.artpro.member.dto.MessageResponse;
import com.artpro.artpro.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final AuthRepository authRepository;

    @Transactional
    public MessageResponse create(RegisterRequest dto) {
        validateRequest(dto);
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = authMapper.mapToEntity(dto, password);
        authRepository.save(member);
        return new MessageResponse(ResponseMessage.CREATE_SUCCESS.getMessage());
    }

    private void validateRequest(RegisterRequest dto) {
        validateEmail(dto);
        validatePassword(dto);
    }

    private void validateEmail(RegisterRequest dto) {
        if (authRepository.existsByEmail(dto.getEmail())) {
            throw new ExistingEmailException();
        }
    }

    private void validatePassword(RegisterRequest dto) {
        if (!dto.getPassword().equals(dto.getCheckPassword())) {
            throw new MismatchPasswordException();
        }
    }

    public boolean checkEmail(EmailRequest emailRequest) {
        return authRepository.existsByEmail(emailRequest.email());
    }

    public boolean checkNickname(NicknameRequest nickname) {
        return authRepository.existsByNickname(nickname.nickname());
    }

    public boolean checkPassword(PasswordRequest dto) {
        return dto.password().equals(dto.checkPassword());
    }
}
