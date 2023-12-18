package com.fastcampus.pass.controller.pass;

import com.fastcampus.pass.config.TestSecurityConfig;
import com.fastcampus.pass.dto.request.PassSendRequest;
import com.fastcampus.pass.service.pass.PassService;
import com.fastcampus.pass.util.FormDataEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - Pass(이용권)")
@Import({TestSecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(PassViewController.class)
class PassViewControllerTest {
    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean
    private PassService passService;

    PassViewControllerTest(@Autowired MockMvc mvc, @Autowired FormDataEncoder formDataEncoder) { //
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @WithUserDetails(value = "userTest", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[view][POST] 패스(이용권) 다른사람에게 이전 - 정상 호출")
    @Test
    void givenRemainPassInfo_whenRequesting_thenSaveRemainPassValueToSellectedUserPass() throws Exception {

        // Given
        String sellectedUserId = "user2";
        PassSendRequest request = PassSendRequest.of(sellectedUserId);
        willDoNothing().given(passService).sendPass(anyString(), anyString());

        // When & Then
        mvc.perform(
                        post("/pass/send")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(request))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pass"))
                .andExpect(redirectedUrl("/pass"));
        then(passService).should().sendPass(anyString(), anyString());
    }

}