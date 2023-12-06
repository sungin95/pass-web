package com.fastcampus.pass.repository.user.security;

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

public record BoardAdminPrincipal(
        String userId,
        String password,
        String nickname,
        Collection<? extends GrantedAuthority> authorities,
        Map<String, Object>oAuth2Attributes
) implements UserDetails, OAuth2User {
    public static BoardAdminPrincipal of(String userId, String password, String nickname, Set<RoleType> roleTypes) {
        return BoardAdminPrincipal.of(userId, password, nickname, roleTypes, Map.of());
    }

    public static BoardAdminPrincipal of(String userId, String password, String nickname, Set<RoleType> roleTypes, Map<String, Object> oAuth2Attributes) {

        return new BoardAdminPrincipal(
                userId,
                password,
                nickname,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                oAuth2Attributes
        );
    }

    public static BoardAdminPrincipal from(UserDto dto) {
        return BoardAdminPrincipal.of(
                dto.userId(),
                dto.userPassword(),
                dto.nickname(),
                dto.roleTypes()
        );
    }

//    public UserDto toDto() {
//        return UserDto.of(
//                userId,
//                password,
//                nickname
//                authorities.stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .map(RoleType::valueOf)
//                        .collect(Collectors.toUnmodifiableSet())
//        );
//    }

    @Override
    public String getPassword() {
        return password;
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
