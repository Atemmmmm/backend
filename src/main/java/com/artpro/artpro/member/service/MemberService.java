package com.artpro.artpro.member.service;

import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.board.repository.BoardRepository;
import com.artpro.artpro.file.repository.FileRepository;
import com.artpro.artpro.heart.entity.Heart;
import com.artpro.artpro.heart.repository.HeartRepository;
import com.artpro.artpro.member.dto.LoginRequest;
import com.artpro.artpro.member.dto.ProfileResponse;
import com.artpro.artpro.member.dto.TokenResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.exception.MemberNotFoundException;
import com.artpro.artpro.member.jwt.JwtTokenProvider;
import com.artpro.artpro.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final BoardRepository boardRepository;
    private final HeartRepository heartRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public TokenResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication, member.getId());
    }

    @Transactional
    public void updateProfileImage(String email, MultipartFile image) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
        String url = fileRepository.save(image);
        member.updateProfileImage(url);
    }

    public ProfileResponse findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        return new ProfileResponse(member);
    }

    public Page<BoardResponse> findBoardsByMemberId(Pageable pageable, Long memberId) {
        pageable = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize());
        return boardRepository.findAllByMemberId(pageable, memberId)
                .map(BoardResponse::new);
    }

    public Page<BoardResponse> findBoardsByHeart(Pageable pageable, Long memberId) {
        pageable = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize());
        return heartRepository.findHeartByMemberId(pageable, memberId)
                .map(Heart::getBoard)
                .map(BoardResponse::new);
    }
}