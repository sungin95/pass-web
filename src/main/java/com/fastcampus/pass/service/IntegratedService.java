package com.fastcampus.pass.service;

import com.fastcampus.pass.dto.UserDto;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.service.pass.PassService;
import com.fastcampus.pass.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class IntegratedService {
    private final UserService userService;
    private final PassService passService;

    public UserDto saveUserAndPass(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone) {
        UserDto userDto = userService.saveUser(userId, userPassword, email, nickname, status, roleTypes, phone);
        passService.createPass(userId);
        return userDto;
    }

}