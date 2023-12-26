package com.artpro.artpro.member.service;

import com.artpro.artpro.member.constant.ResponseMessage;
import com.artpro.artpro.member.dto.*;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.exception.MemberNotFoundException;
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
    public MessageResponse create(RegisterRequest dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = memberMapper.mapToEntity(dto, password);
        memberRepository.save(member);
        return new MessageResponse(ResponseMessage.CREATE_SUCCESS.getMessage());
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication, member.getId());
    }

    public boolean checkEmail(EmailRequest emailRequest) {
        return memberRepository.existsByEmail(emailRequest.getEmail());
    }

    public boolean checkNickname(NicknameRequest nickname) {
        return memberRepository.existsByNickname(nickname.getNickname());
    }

    public boolean checkPassword(PasswordRequest dto) {
        return dto.getPassword().equals(dto.getCheckPassword());
    }
}