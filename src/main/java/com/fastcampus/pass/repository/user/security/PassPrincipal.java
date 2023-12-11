package com.fastcampus.pass.repository.user.security;

import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.repository.user.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record PassPrincipal(
        String userId,
        String userPassword,
        String email,
        String nickname,
        UserStatus status,
        Collection<? extends GrantedAuthority> authorities,
        String phone,
        Long remainingDaysAtGym,
        String meta,
        Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {
    public static PassPrincipal of(String userId,
                                   String userPassword,
                                   String email,
                                   String nickname,
                                   UserStatus status,
                                   Set<RoleType> roleTypes,
                                   String phone,
                                   Long remainingDaysAtGym,
                                   String meta
    ) {
        return PassPrincipal.of(userId, userPassword, email, nickname, status, roleTypes, phone, remainingDaysAtGym, meta, Map.of());
    }

    public static PassPrincipal of(String userId,
                                   String userPassword,
                                   String email,
                                   String nickname,
                                   UserStatus status,
                                   Set<RoleType> roleTypes,
                                   String phone,
                                   Long remainingDaysAtGym,
                                   String meta,
                                   Map<String, Object> oAuth2Attributes) {

        return new PassPrincipal(
                userId,
                userPassword,
                email,
                nickname,
                status,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                phone,
                remainingDaysAtGym,
                meta,
                oAuth2Attributes
        );
    }

    public static PassPrincipal from(UserDto dto) {
        return PassPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname(),
                dto.status(),
                dto.roleTypes(),
                dto.phone(),
                dto.remainingDaysAtGym(),
                dto.meta()
        );
    }

    public UserDto toDto() {
        return UserDto.of(
                userId,
                userPassword,
                email,
                nickname,
                status,
                authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(RoleType::valueOf)
                        .collect(Collectors.toUnmodifiableSet()),
                phone,
                remainingDaysAtGym,
                meta
        );
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 이 아래로는 현재 건들 계획이 없음
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }


    @Override
    public String getName() {
        return userId;
    }

}
