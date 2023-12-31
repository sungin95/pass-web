package com.fastcampus.pass.controller;

import com.fastcampus.pass.dto.PassDto;
import com.fastcampus.pass.dto.request.PassSendRequest;
import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.dto.security.PassPrincipal;
import com.fastcampus.pass.service.PassService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/pass")
public class PassViewController {
    private final PassService passService;

    public PassViewController(PassService passService) {
        this.passService = passService;
    }

    @GetMapping
    public ModelAndView getPass(@AuthenticationPrincipal PassPrincipal passPrincipal) {
        ModelAndView modelAndView = new ModelAndView();

        PassEntity pass = passService.getPass(passPrincipal.userId());
        final PassDto passDto = PassDto.from(pass);


        modelAndView.addObject("pass", passDto);
        modelAndView.addObject("user", passPrincipal);
        modelAndView.setViewName("pass/index");

        return modelAndView;
    }

    @PostMapping("/send")
    public String postPackagePurchase(
            @AuthenticationPrincipal PassPrincipal passPrincipal,
            PassSendRequest passSendRequest
    ) {
        passService.sendPass(passPrincipal.userId(), passSendRequest.sellectedUserId());

        return "redirect:/pass";
    }

}