package com.fastcampus.pass.service.user;

import com.fastcampus.pass.repository.user.UserDto;
import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserRepository;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.service.pass.PassService;
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
    private final PassService passService;

    @Transactional(readOnly = true)
    public Optional<UserDto> searchUser(final String userId) {
        return userRepository.findById(userId)
                .map(UserDto::from);
    }

    public UserDto saveUser(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone) {
        UserDto userDto = UserDto.from(userRepository.save(UserEntity.of(userId, userPassword, email, nickname, status, roleTypes, phone)));
        passService.createPass(userDto.userId());
        return userDto;
    }

    @Transactional(readOnly = true)
    public List<UserDto> users() {

        return userRepository.findAll().stream()
                .map(UserDto::from)
                .toList();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }


}