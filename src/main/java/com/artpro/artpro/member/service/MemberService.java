package com.artpro.artpro.member.service;

import com.artpro.artpro.member.constant.ResponseMessage;
import com.artpro.artpro.member.dto.*;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.exception.ExistingMemberException;
import com.artpro.artpro.member.exception.ExistingNicknameException;
import com.artpro.artpro.member.exception.MismatchPasswordException;
import com.artpro.artpro.member.jwt.JwtTokenProvider;
import com.artpro.artpro.member.mapper.MemberMapper;
import com.artpro.artpro.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(RegisterRequest dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = memberMapper.mapToEntity(dto, password);
        memberRepository.save(member);
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Member member = memberRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        return jwtTokenProvider.generateToken(authentication, member.getId());
    }

    public MessageResponse checkEmail(EmailRequest emailRequest) {
        memberRepository.findByEmail(emailRequest.getEmail())
                .ifPresent(e -> {
                    throw new ExistingMemberException();
                });
        return new MessageResponse(ResponseMessage.AVAILABLE_EMAIL.getMessage());
    }

    public MessageResponse checkNickname(NicknameRequest nickname) {
        memberRepository.findByNickname(nickname.getNickname())
                .ifPresent(e -> {
                    throw new ExistingNicknameException();
                });
        return new MessageResponse(ResponseMessage.AVAILABLE_NICKNAME.getMessage());
    }

    public MessageResponse checkPassword(PasswordRequest dto) {
        if (!dto.getPassword().equals(dto.getCheckPassword())) {
            throw new MismatchPasswordException();
        }
        return new MessageResponse(ResponseMessage.MATCH_PASSWORD.getMessage());
    }
}