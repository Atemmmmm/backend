package com.artpro.artpro.auth.controller;

import com.artpro.artpro.auth.dto.EmailRequest;
import com.artpro.artpro.auth.dto.NicknameRequest;
import com.artpro.artpro.auth.dto.PasswordRequest;
import com.artpro.artpro.auth.dto.RegisterRequest;
import com.artpro.artpro.member.constant.ResponseMessage;
import com.artpro.artpro.member.dto.MessageResponse;
import com.artpro.artpro.member.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    @DisplayName("모든 정보를 입력하여 요청을 보내면 회원 정보가 저장된다.")
    void 정상_회원가입() {
        String email = "artpro@gmail.com";
        String nickName = "artpro";
        String password = "1234";
        String checkPassword = "1234";
        Role role = Role.ARTIST;
        RegisterRequest registerRequest = new RegisterRequest(email, nickName, password, checkPassword, role);

        MessageResponse response = authController.register(registerRequest);
        assertThat(response.getMessage()).isEqualTo(ResponseMessage.CREATE_SUCCESS.getMessage());
    }

    @Test
    @DisplayName("존재하는 이메일일 경우 true를 반환한다.")
    void 존재하는_이메일() {
        String email = "artpro@gmail.com";
        EmailRequest request = new EmailRequest(email);

        boolean isSame = authController.checkEmail(request);

        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 이메일일 경우 false를 반환한다.")
    void 존재하지_않는_이메일() {
        String email = "artproppro@gmail.com";
        EmailRequest request = new EmailRequest(email);

        boolean isSame = authController.checkEmail(request);

        assertThat(isSame).isFalse();
    }

    @Test
    @DisplayName("존재하는 닉네임일 경우 true를 반환한다.")
    void 존재하는_닉네임() {
        String nickname = "artpro";
        NicknameRequest request = new NicknameRequest(nickname);

        boolean isSame = authController.checkNickname(request);

        assertThat(isSame).isTrue();
    }


    @Test
    @DisplayName("입력받은 패스워드끼리 일치하지 않는 경우 false를 반환한다.")
    void 일치하지_않는_패스워드() {
        String password = "artpro";
        String checkPassword = "1234";
        PasswordRequest request = new PasswordRequest(password, checkPassword);

        boolean isSame = authController.checkPassword(request);

        assertThat(isSame).isFalse();
    }

    @Test
    @DisplayName("입력받은 패스워드끼리 일치하는 경우 true를 반환한다.")
    void 일치하는_패스워드() {
        String password = "artpro";
        String checkPassword = "artpro";
        PasswordRequest request = new PasswordRequest(password, checkPassword);

        boolean isSame = authController.checkPassword(request);

        assertThat(isSame).isTrue();
    }
}
