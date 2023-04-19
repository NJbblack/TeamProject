package com.bb.voyage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.service.PkgService;

@Controller
public class PageController {
    @Autowired
    PkgService pkgService;
/////////////////////////////////////////////////////////////////////
////기타 Page 매핑용 컨트롤러  
    @GetMapping("/company/companyinfo")
    public String companyinfo(){
        return "/company/companyinfo";
    }

    @GetMapping("/company/companypolicy")
    public String companypolicy(){
        return "/company/companypolicy";
    }

    @GetMapping("/company/companyprofile")
    public String companyprofile(){
        return "/company/companyprofile";
    }

    @GetMapping("/contact/contact")
    public String contact(){
        return "/contact/contact";
    }

    @GetMapping("/contact/reservation")
    public String reservation(){
        return "/contact/reservation";
    }

    @GetMapping("/country/china")
    public String china(Model model){
        List<PkgDto> pkgList = pkgService.pkgChinaList();
        model.addAttribute("pkgList", pkgList);
        return "/country/china";
    }

    @GetMapping("/country/japan")
    public String japan(Model model){
        List<PkgDto> pkgList = pkgService.pkgJapanList();
        model.addAttribute("pkgList", pkgList);
        return "/country/japan";
    }

    @GetMapping("/country/korea")
    public String korea(Model model){
        List<PkgDto> pkgList = pkgService.pkgKoreaList();
        model.addAttribute("pkgList", pkgList);
        return "/country/korea";
    }

    @GetMapping("/transportation/airplane")
    public String airplane(){
        return "/transportation/airplane";
    }

    @GetMapping("/transportation/bus")
    public String bus(){
        return "/transportation/bus";
    }
    
    @GetMapping("/transportation/train")
    public String train(){
        return "/transportation/train";
    }

}
