package com.Ecommerce.Shop.services;

import com.Ecommerce.Shop.dtos.User.UserChangePasswordDto;
import com.Ecommerce.Shop.dtos.User.UserDto;
import com.Ecommerce.Shop.utils.constants.Messages;
import com.Ecommerce.Shop.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Ecommerce.Shop.repositories.UserRepository;
import com.Ecommerce.Shop.utils.functions.GettingUser;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Result ChangePassword(UserChangePasswordDto userChangePasswordDto) {
        var user = GettingUser.Get();

        boolean isPasswordCorrect = passwordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword());
        if (!isPasswordCorrect) {
            return new ErrorResult(Messages.wrong_password.toString());
        }

        // Si la contraseña es correcta, actualiza la contraseña del usuario
        String encodedPassword = passwordEncoder.encode(userChangePasswordDto.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new SuccessResult(Messages.success.toString());
    }

    public DataResult<UserDto> GetMyInfo() {
        var user = GettingUser.Get();
        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return new SuccessDataResult<>(userDto, Messages.success.toString());
    }

    public Result Delete(Integer userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ErrorResult(Messages.user_not_found.toString());
        }
        userRepository.delete(user.get());
        return new SuccessResult(Messages.success.toString());
    }
}
 