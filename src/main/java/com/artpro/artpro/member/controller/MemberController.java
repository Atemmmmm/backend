package com.artpro.artpro.member.controller;

import com.artpro.artpro.board.dto.response.BoardResponse;
import com.artpro.artpro.member.dto.LoginRequest;
import com.artpro.artpro.member.dto.ProfileResponse;
import com.artpro.artpro.member.dto.TokenResponse;
import com.artpro.artpro.member.entity.Member;
import com.artpro.artpro.member.service.MemberService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseBody
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return memberService.login(loginRequest);
    }

    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateProfileImage(@AuthenticationPrincipal Member member,
                                   @Parameter(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
                                   @RequestPart MultipartFile image) {
        memberService.updateProfileImage(member.getEmail(), image);
    }

    @GetMapping("/boards")
    public Page<BoardResponse> findBoardsByMemberId(@PageableDefault(size = 3) Pageable pageable,
                                                    @AuthenticationPrincipal Member member) {
        return memberService.findBoardsByMemberId(pageable, member.getId());
    }

    @GetMapping("/hearts")
    public Page<BoardResponse> findBoardsByHeart(@PageableDefault(size = 3) Pageable pageable,
                                                 @AuthenticationPrincipal Member member) {
        return memberService.findBoardsByHeart(pageable, member.getId());
    }

    @GetMapping
    public ProfileResponse findProfileByMemberId(@AuthenticationPrincipal Member member) {
        return memberService.findMemberById(member.getId());
    }
}