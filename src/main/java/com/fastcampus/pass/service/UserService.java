package com.fastcampus.pass.service;

import com.fastcampus.pass.dto.UserDto;
import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserRepository;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserDto> searchUser(final String userId) {
        return userRepository.findById(userId)
                .map(UserDto::from);
    }

    // test외에는 사용 금지. User생성시 반드시 saveUserAndPass를 사용 할 것.
    public UserDto saveUser(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone) {
        return UserDto.from(userRepository.save(UserEntity.of(userId, userPassword, email, nickname, status, roleTypes, phone)));
    }

//    @Transactional(readOnly = true)
//    public List<UserDto> users() {
//
//        return userRepository.findAll().stream()
//                .map(UserDto::from)
//                .toList();
//    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}