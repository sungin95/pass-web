package com.fastcampus.pass.repository.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {

    USER("ROLE_USER", "사용자"),
    TEACHER("ROLE_TEACHER", "강사님"),
    ADMIN("ROLE_ADMIN", "관리자");

    @Getter
    private final String roleName;
    @Getter
    private final String description;

}