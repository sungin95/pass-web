package com.fastcampus.pass.service;


import com.fastcampus.pass.repository.user.UserDto;
import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserRepository;
import com.fastcampus.pass.repository.user.UserStatus;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 회원")
@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @InjectMocks
    private UserService sut;

    @Mock
    private UserRepository userAccountRepository;

    @DisplayName("존재하는 회원 ID를 검색하면, 회원 데이터를 Optional로 반환한다.")
    @Test
    void givenExistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "uno";
        given(userAccountRepository.findById(username)).willReturn(Optional.of(createUserAccount(username)));

        // When
        Optional<UserDto> result = sut.searchUser(username);

        // Then
        assertThat(result).isPresent();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("존재하지 않는 회원 ID를 검색하면, 비어있는 Optional을 반환한다.")
    @Test
    void givenNonexistentUserId_whenSearching_thenReturnsOptionalUserData() {
        // Given
        String username = "wrong-user";
        given(userAccountRepository.findById(username)).willReturn(Optional.empty());

        // When
        Optional<UserDto> result = sut.searchUser(username);

        // Then
        assertThat(result).isEmpty();
        then(userAccountRepository).should().findById(username);
    }

    @DisplayName("회원 정보를 입력하면, 새로운 회원 정보를 저장하여 가입시키고 해당 회원 데이터를 리턴한다.")
    @Test
    void givenUserParams_whenSaving_thenSavesUserAccount() {
        // Given
        UserEntity userAccount = createUserAccount("uno");
        UserEntity savedUserAccount = createUserAccount("uno");
        given(userAccountRepository.save(userAccount)).willReturn(savedUserAccount);

        // When
        UserDto result = sut.saveUser(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getStatus(),
                userAccount.getRoleTypes(),
                userAccount.getPhone(),
                userAccount.getRemainingDaysAtGym()
        );

        // Then
        assertThat(result)
                .hasFieldOrPropertyWithValue("userId", userAccount.getUserId());
        then(userAccountRepository).should().save(userAccount);
    }



    private UserEntity createUserAccount(String userId) {
        return UserEntity.of(
                userId,
                "password",
                "dlrk@naver.com",
                "nickname",
                UserStatus.INACTIVE,
                Set.of(RoleType.USER),
                "01012345678",
                0L
        );
    }

}

