package com.bb.voyage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.service.PkgService;


@Controller
public class HomeController {

  @Autowired
  PkgService pkgService;

  @GetMapping({"/","/index"})
  public String home(Model model) {
    List<PkgDto> recommededPkgList = pkgService.getRecommendedPkgList();
    List<PkgDto> discountedPkgList = pkgService.getDiscountedPkgList();
    model.addAttribute("recommededPkgList", recommededPkgList);
    model.addAttribute("discountedPkgList", discountedPkgList);
    return "/index/index";
  }
}
