package com.fastcampus.pass.service;

import com.fastcampus.pass.dto.PackageDto;
import com.fastcampus.pass.repository.packaze.PackageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 패키지")
@ExtendWith(MockitoExtension.class)
class PackageServiceTest {
    @InjectMocks
    private PackageService sut;

    @Mock
    private PackageRepository packageRepository;

    @DisplayName("해당 페이지를 들어가면, 전체 패키지를 조회한다.")
    @Test
    void given_whenFindAllPackages_thenReturnsAllPackagesOrderByPackageName() {
        // Given
        given(packageRepository.findAllByOrderByPackageName()).willReturn(List.of());

        // When
        List<PackageDto> allPackages = sut.getAllPackages();

        // Then
        assertThat(allPackages).isEmpty();
        then(packageRepository).should().findAllByOrderByPackageName();
    }


}