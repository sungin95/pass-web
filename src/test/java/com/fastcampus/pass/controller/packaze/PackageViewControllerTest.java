package com.fastcampus.pass.controller.packaze;

import com.fastcampus.pass.config.TestSecurityConfig;
import com.fastcampus.pass.dto.request.PackagePurchaseRequest;
import com.fastcampus.pass.service.packaze.PackageService;
import com.fastcampus.pass.service.pass.PassService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import com.fastcampus.pass.util.FormDataEncoder;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 패키지")
@Import({TestSecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(PackageViewController.class)
class PackageViewControllerTest {
    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean
    private PackageService packageService;

    @MockBean
    private PassService passService;

    PackageViewControllerTest(@Autowired MockMvc mvc, @Autowired FormDataEncoder formDataEncoder) { //
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @WithUserDetails(value = "userTest", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[view][POST] 패키지 구매 - 정상 호출")
    @Test
    void givenPackageInfo_whenRequesting_thenSavePackageOptionToLoginUserPass() throws Exception {
        // Given
        int packagePeriod = 30;
        int packagePT = 5;
        PackagePurchaseRequest request = PackagePurchaseRequest.of(packagePeriod, packagePT);
        willDoNothing().given(passService).purchasePackaze(anyString(), anyInt(), anyInt());
        // When & Then
        mvc.perform(
                post("/packaze/purchase")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(formDataEncoder.encode(request))
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pass"))
                .andExpect(redirectedUrl("/pass"));
        then(passService).should().purchasePackaze(anyString(), anyInt(), anyInt());
    }

}