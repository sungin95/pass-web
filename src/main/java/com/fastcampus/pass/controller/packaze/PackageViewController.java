package com.fastcampus.pass.controller.packaze;

import com.fastcampus.pass.repository.packaze.PackageDto;
import com.fastcampus.pass.repository.user.security.PassPrincipal;
import com.fastcampus.pass.service.packaze.PackageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/packaze")
public class PackageViewController {

    private final PackageService packageService;

    public PackageViewController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public ModelAndView getPackaze(@AuthenticationPrincipal PassPrincipal passPrincipal) {
        ModelAndView modelAndView = new ModelAndView();

        final List<PackageDto> packages = packageService.getAllPackages();

        modelAndView.addObject("packages", packages);
        modelAndView.setViewName("packaze/index");

        return modelAndView;
    }
}
