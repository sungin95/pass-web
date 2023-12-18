package com.fastcampus.pass.service;

import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.repository.pass.PassRepository;
import com.fastcampus.pass.repository.pass.PassStatus;
import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - Pass(이용권)")
@ExtendWith(MockitoExtension.class)
class PassServiceTest {
    @InjectMocks
    private PassService sut;

    @Mock
    private PassRepository passRepository;

    @DisplayName("내 이용권 페이지에 가면, 내 이용권을 보여준다.")
    @Test
    void givenLoginInfo_whenGetPass_thenReturnsMyPass() {

        // Given
        UserEntity user = createUser("testuser");
        PassEntity pass = createPass("testuser");
        given(passRepository.findByUserId("testuser")).willReturn(List.of(pass));

        // When
        PassEntity MyPass = sut.getPass("testuser");

        // Then
        assertThat(MyPass)
                .hasFieldOrPropertyWithValue("userId", "testuser")
                .hasFieldOrPropertyWithValue("gymPeriod", 0)
                .hasFieldOrPropertyWithValue("countPt", 0)
                .hasFieldOrPropertyWithValue("status", "DEACTIVATION")
        ;
        then(passRepository).should().findByUserId("testuser");
    }

    private UserEntity createUser(String userId) {
        return UserEntity.of(
                userId,
                "password",
                "dlrk@naver.com",
                "nickname",
                UserStatus.INACTIVE,
                Set.of(RoleType.USER),
                "01012345678"
        );

    }

    private PassEntity createPass(String userId) {
        return PassEntity.of(userId, 0, 0, PassStatus.DEACTIVATION.toString(), userId);
    }


}