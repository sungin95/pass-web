package com.fastcampus.pass.config;

import com.fastcampus.pass.repository.user.UserDto;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.service.IntegratedService;
import com.fastcampus.pass.service.user.UserService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserService userService;

    @MockBean
    private IntegratedService integratedService;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(integratedService.saveUserAndPass(anyString(), anyString(),anyString(),anyString(),any(),anySet(),anyString()))
                .willReturn(createUserAccountDto());
    }

    private UserDto createUserAccountDto() {
        return UserDto.of(
                "user_Test",
                "password",
                "user-test@email.com",
                "user-test",
                UserStatus.INACTIVE,
                Set.of(RoleType.USER),
                "01012345678",
                null
        );
    }

}
