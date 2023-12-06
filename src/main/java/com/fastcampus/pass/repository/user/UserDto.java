package com.fastcampus.pass.repository.user;

import com.fastcampus.pass.repository.user.constant.RoleType;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public record UserDto(
        String userId,
        String userPassword,
        String email,
        String nickname,
        UserStatus status,
        Set<RoleType> roleTypes,
        String phone,
        Long remainingDaysAtGym,
        String meta,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserDto first_create(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, Long remainingDaysAtGym, String meta) {
        return UserDto.of(userId, userPassword, email, nickname, status, roleTypes, phone, 0L, null);
    }

    public static UserDto of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, Long remainingDaysAtGym, String meta) {
        return UserDto.of(userId, userPassword, email, nickname, status, roleTypes, phone, remainingDaysAtGym, meta, null, null, null, null);
    }

    public static UserDto of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, Long remainingDaysAtGym, String meta, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserDto(userId, userPassword, email, nickname, status, roleTypes, phone, remainingDaysAtGym, meta, createdAt, createdBy, modifiedAt, modifiedBy);
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
                entity.getRemainingDaysAtGym(),
                entity.getMeta(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
