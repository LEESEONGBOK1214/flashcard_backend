package com.teosprint.flashcard.dto;


import com.teosprint.flashcard.entity.User;
import com.teosprint.flashcard.entity.enums.AuthorityEnums;
import com.teosprint.flashcard.entity.enums.LoginTypeEnums;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLogin {
        @Schema(name = "아이디", description = "id")
        private String username;
        @Schema(name = "비밀번호", description = "id")
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(username, password);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginResponse extends UserInfo {
        @Schema(name = "AccessToken", description = "인증 토큰")
        private String accessToken;
        @Schema(name = "RefreshToken", description = "재발급 토큰")
        private String refreshToken;

        public UserLoginResponse(User user, String at, String rt) {
            super(user);
            this.accessToken = at;
            this.refreshToken = rt;
        }
    }

    @Data
    @NoArgsConstructor
    @Schema(name = "사용자 계정 정보", description = "계정에 대한 대략적인 정보를 반환")
    public static class UserInfo {
        @Schema(name = "사용자 계정 아이디", description = "id")
        private String username;
        @Schema(name = "사용자 계정 권한", description = "권한 정보 role_admin, role_user 같이 사용")
        private String role;

        @Schema(name = "사용자 로그인 타입", description = "깃헙, 구글, 애플 등 로그인 타입")
        private LoginTypeEnums loginType;

        public UserInfo(User user) {
            this.username = user.getUsername();
            this.role = user.getAuthority();
            this.loginType = user.getLoginType();
        }
    }

    @Getter
    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPost {
        @Schema(name = "등록할 아이디", description = "id", required = true)
        @Length(min = 0, max = 255, message = "username은 0~255자만 가능합니다.")
        @NotBlank(message = "username은 필수입력입니다.")
        private String username;
        @Schema(name = "등록할 비밀번호", description = "password", required = true)
        @NotBlank(message = "password는 필수입력입니다.")
        private String password;

        @Schema(name = "등록할 로그인 타입",
                description = "깃헙, 구글, 애플 등 로그인 타입",
                allowableValues = {"FLASHCARD", "GIT", "GOOGLE", "APPLE"},
                defaultValue = "FLASHCARD",
                required = true,
                example = "FLASHCARD")
        @NotNull(message = "loginType은 필수입력입니다.")
        private LoginTypeEnums loginType;

        public void setEncryptPassword(String password) {
            this.password = password;
        }

        public User toEntity() {
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .authority(AuthorityEnums.USER.getValue())
                    .loginType(this.loginType)
                    .build();

        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdate {
        @Schema(name = "수정할 아이디", description = "id")
        private String username;
        @Schema(name = "수정할 비밀번호", description = "id")
        private String password;
    }

}
