package com.teosprint.flashcard.entity;

import com.teosprint.flashcard.entity.enums.LoginTypeEnums;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity // 엔티티(테이블엔티티) 임을 알림

// schema=flashcard는 데이터베이스라 고정 name=테이블명
@Table(schema = "flashcard", name = "tb_user")
@Getter
@Setter

@NoArgsConstructor
public class User implements UserDetails {

    // oauth 같은 경우 key를 받아서 할 예정이라 길어질 수 있음.
    @Id
    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "authority", nullable = false, length = 255)
    private String authority = "ROLE_USER";

    @Column(name = "login_type", nullable = false, length = 255)
    @Comment("로컬, 구글, 애플, 깃 등 auth 로그인 타입")
    private LoginTypeEnums loginType = LoginTypeEnums.FLASHCARD;

    @Column(name = "is_non_expired", nullable = false)
    private boolean accountNonExpired=true;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean accountNonLocked=true;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired=true;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled=true;

    @Builder
    public User(String username, String password, String authority, LoginTypeEnums loginType) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.loginType = loginType;
    }

    public User(String username, Collection<? extends GrantedAuthority> authorities) {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.authority));
        return authorities;
    }
}
