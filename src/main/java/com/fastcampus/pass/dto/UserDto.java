package com.fastcampus.pass.dto;

import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        String userId,
        String userPassword,
        String email,
        String nickname,
        UserStatus status,
        Set<RoleType> roleTypes,
        String phone,
        String meta,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserDto of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, String meta) {
        return UserDto.of(userId, userPassword, email, nickname, status, roleTypes, phone, meta, null, null, null, null);
    }

    public static UserDto of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, String meta, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserDto(userId, userPassword, email, nickname, status, roleTypes, phone, meta, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserDto from(UserEntity entity) {
        return new UserDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getStatus(),
                entity.getRoleTypes(),
                entity.getPhone(),
                entity.getMeta(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
