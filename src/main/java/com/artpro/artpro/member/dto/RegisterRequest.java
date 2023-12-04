package com.artpro.artpro.member.dto;

import com.artpro.artpro.member.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private final String email;

    @NotBlank(message = "닉네임은 필수 입력사항 입니다.")
    private final String nickname;

    @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
    private final String password;

    @NotBlank(message = "비밀번호를 확인하여야 합니다.")
    private final String checkPassword;

    private final Role role;
}