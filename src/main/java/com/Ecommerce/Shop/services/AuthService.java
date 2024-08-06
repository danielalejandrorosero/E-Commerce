package com.Ecommerce.Shop.services;


import com.Ecommerce.Shop.dtos.Auth.AuthLoginDto;
import com.Ecommerce.Shop.dtos.Auth.AuthRegisterDto;
import com.Ecommerce.Shop.dtos.Auth.AuthResponseDto;
import com.Ecommerce.Shop.entities.User;
import com.Ecommerce.Shop.repositories.UserRepository;
import com.Ecommerce.Shop.utils.constants.Messages;
import com.Ecommerce.Shop.utils.results.DataResult;
import com.Ecommerce.Shop.utils.results.ErrorDataResult;
import com.Ecommerce.Shop.utils.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Ecommerce.Shop.utils.jwt.JwtService;
import com.Ecommerce.Shop.enums.User.Role;
@Service
@RequiredArgsConstructor

public class AuthService {
    // Constantes
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public DataResult<AuthResponseDto> signUp(AuthRegisterDto request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new SuccessDataResult<AuthResponseDto>(AuthResponseDto.builder()
                .token(jwtToken)
                .build());
    }

    public DataResult<AuthResponseDto> signIn(AuthLoginDto request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (user.toString().equals("Optional.empty"))
            return new ErrorDataResult<AuthResponseDto>(null, Messages.email_not_found.toString());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);

        return new SuccessDataResult<AuthResponseDto>(AuthResponseDto.builder()
                .token(jwtToken)
                .build(), Messages.success.toString());

    }

}