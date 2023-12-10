package com.fastcampus.pass.controller.pass;

import com.fastcampus.pass.repository.pass.PassDto;
import com.fastcampus.pass.repository.user.UserDto;
import com.fastcampus.pass.repository.user.security.PassPrincipal;
import com.fastcampus.pass.service.pass.PassService;
import com.fastcampus.pass.service.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/passes")
public class PassViewController {
    private final PassService passService;

    public PassViewController(UserService userService, PassService passService) {
        this.passService = passService;
    }

    @GetMapping
    public ModelAndView getPasses(@AuthenticationPrincipal PassPrincipal passPrincipal) {
        ModelAndView modelAndView = new ModelAndView();

        final List<PassDto> passes = passService.getPasses(passPrincipal.userId());

        modelAndView.addObject("passes", passes);
        modelAndView.addObject("user", passPrincipal);
        modelAndView.setViewName("pass/index");

        return modelAndView;
    }

}