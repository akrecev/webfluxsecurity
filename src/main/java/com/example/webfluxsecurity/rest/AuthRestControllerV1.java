package com.example.webfluxsecurity.rest;

import com.example.webfluxsecurity.dto.AuthRequestDto;
import com.example.webfluxsecurity.dto.AuthResponseDto;
import com.example.webfluxsecurity.dto.UserDto;
import com.example.webfluxsecurity.entity.UserEntity;
import com.example.webfluxsecurity.mapper.UserMapper;
import com.example.webfluxsecurity.repository.UserRepository;
import com.example.webfluxsecurity.security.CustomPrincipal;
import com.example.webfluxsecurity.security.SecurityService;
import com.example.webfluxsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestControllerV1 {
    private final SecurityService securityService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public Mono<UserDto> register(@RequestBody UserDto dto) {
        UserEntity entity = userMapper.map(dto);

        return userService.registerUser(entity)
                .map(userMapper::map);
    }

    public Mono<AuthResponseDto> login(@RequestBody AuthRequestDto dto) {
        return securityService.authenticate(dto.getUserName(), dto.getPassword())
                .flatMap(tokenDetails -> Mono.just(
                        AuthResponseDto.builder()
                                .userId(tokenDetails.getUserId())
                                .token(tokenDetails.getToken())
                                .issuedAt(tokenDetails.getIssuedAt())
                                .expiresAt(tokenDetails.getExpiresAt())
                                .build()
                ));
    }

    @GetMapping("/info")
    public Mono<UserDto> getUserInfo(Authentication authentication) {
        CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

        return userService.getUserById(customPrincipal.getId())
                .map(userMapper::map);
    }

}
