package com.teosprint.flashcard.service;

import com.teosprint.flashcard.config.exception.UnauthorizedException;
import com.teosprint.flashcard.config.exception.user.PostAlreadyExistUserException;
import com.teosprint.flashcard.config.jwt.JwsTokenProvider;
import com.teosprint.flashcard.config.jwt.TokenDto;
import com.teosprint.flashcard.dto.UserDto;
import com.teosprint.flashcard.entity.RefreshToken;
import com.teosprint.flashcard.entity.User;
import com.teosprint.flashcard.repository.RefreshTokenRepository;
import com.teosprint.flashcard.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwsTokenProvider tokenProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserById(String id){
        Optional<User> userOpt = findUserById(id);
        if(userOpt.isPresent())return userOpt.get();
        throw new UsernameNotFoundException("ID로 계정을 찾을 수 없습니다.");
    }

    public Optional<User> findUserById(String id){
         return userRepo.findById(id);
    }

    @Override
    public User loadUserByUsername(String username) {
        return getUserById(username);
    }

    public UserDto.UserInfo postUser(UserDto.UserPost dto){
        String username = dto.getUsername();
        try{
            loadUserByUsername(username);
            throw new PostAlreadyExistUserException();
        }catch (UsernameNotFoundException e){
            // 계정이 안 찾아지면 정상처리.
        }
        String encodePw = passwordEncoder.encode(dto.getPassword());
        dto.setEncryptPassword(encodePw);
        User user = dto.toEntity();
        User response = userRepo.save(user);
        UserDto.UserInfo userInfo = new UserDto.UserInfo(response);

        log.info("회원가입 결과 : {}", userInfo);
        return userInfo;
    }

    public TokenDto login(UserDto.UserLogin dto){
        log.info("## 유저 로그인 요청 id " + dto.getUsername());

        // Dto의 email, password를 받고 UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();

        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;

//        User user = loadUserByUsername(dto.getUsername());
//        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
//            throw new UnauthorizedException("로그인에 실패했습니다.");
//        }
//        // TODO 변경
//        return user;
    }

}
