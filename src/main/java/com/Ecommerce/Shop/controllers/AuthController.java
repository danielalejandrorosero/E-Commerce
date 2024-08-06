package com.Ecommerce.Shop.controllers;


import com.Ecommerce.Shop.dtos.Auth.AuthLoginDto;
import com.Ecommerce.Shop.dtos.Auth.AuthRegisterDto;
import com.Ecommerce.Shop.dtos.Auth.AuthResponseDto;
import com.Ecommerce.Shop.utils.results.DataResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Ecommerce.Shop.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<DataResult<AuthResponseDto>> register(@Valid @RequestBody AuthRegisterDto request) {
        var result = authService.signUp(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PostMapping("/signIn")
    public ResponseEntity<DataResult<AuthResponseDto>> Login(@Valid @RequestBody AuthLoginDto request) {
        var result = authService.signIn(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

}
