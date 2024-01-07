package com.artpro.artpro.member.service;

import com.artpro.artpro.global.dto.MemberDto;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository
                .findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    private UserDetails createUserDetails(Member member) {
        return new MemberDto(member);
    }
}