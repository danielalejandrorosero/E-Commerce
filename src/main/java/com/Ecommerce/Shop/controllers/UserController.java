package com.Ecommerce.Shop.controllers;


import com.Ecommerce.Shop.dtos.User.UserChangePasswordDto;
import com.Ecommerce.Shop.services.UserService;
import com.Ecommerce.Shop.utils.results.DataResult;
import com.Ecommerce.Shop.utils.results.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/getMyInfo")
    public ResponseEntity<DataResult> getMyInfo() {
        var result = userService.GetMyInfo();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
    @PutMapping("/changePassword")
    public ResponseEntity<Result> register(@Valid @RequestBody UserChangePasswordDto request) {
        var result = userService.ChangePassword(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer userId) {
        var result = userService.Delete(userId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}