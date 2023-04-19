package com.bb.voyage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.PkgSubImgDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.service.AdminService;
import com.bb.voyage.service.PkgService;
import com.bb.voyage.service.ReservService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/package")
public class PkgController {
    
    @Autowired
    AdminService adminService;
    @Autowired
    PkgService pkgService;
    @Autowired
    ReservService reservService;

/////////////////////////////////////////////////////////////////////
////Package 관련 컨트롤러
    @GetMapping("/pkgkorea")
    public String pkgKoreaList(Model model){
      List<PkgDto> pkgList = pkgService.pkgKoreaList();
      model.addAttribute("pkgList", pkgList);
      return "/package/pkgkorea";
    }

    @GetMapping("/pkgjapan")
    public String pkgJapanList(Model model){
        List<PkgDto> pkgList = pkgService.pkgJapanList();
        model.addAttribute("pkgList", pkgList);
        return "/package/pkgjapan";
    }

    @GetMapping("/pkgchina")
    public String pkgChinaList(Model model){
        List<PkgDto> pkgList = pkgService.pkgChinaList();
        model.addAttribute("pkgList", pkgList);
        return "/package/pkgchina";
    }

    @GetMapping("/pkgdetailview")
    public String pkgViewImg(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      PkgSubImgDto pkgSubImgDto = adminService.getOnePkgSubImg(pkgNo);
      List<ReviewDto> reviewList =  pkgService.getReview(pkgDto);
      model.addAttribute("pkgDto", pkgDto);
      model.addAttribute("pkgSubImgDto", pkgSubImgDto);
      model.addAttribute("reviewList", reviewList);
      return "/package/pkgdetailview";
    }

    @PostMapping("/pkgdetailview")
    @ResponseBody
    public PkgDto pkgViewImg02(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      return pkgDto;
    }

    @PostMapping("/getSelectedReview")
    @ResponseBody
    public ReviewDto getSelectedReview(int reviewNo){
      return pkgService.getSelectedReview(reviewNo);
    }



/////////////////////////////////////////////////////////////////////
////Recommendation 관련 컨트롤러
    @GetMapping("/recommendation")
    public String recommendation(){
      return "/package/recommendation";
    }

    @PostMapping("/getTempRecoList")
    @ResponseBody
    public List<PkgDto> getTempRecoList(PkgDto pkgDto){
      return pkgService.getTempRecoList(pkgDto);
    }

    @PostMapping("/getRecommendedList")
    @ResponseBody
    public List<PkgDto> getRecommendedList(int pkgNo01, int pkgNo02, int pkgNo03, int pkgNo04){
      HashMap hashmap = new HashMap<>();
      hashmap.put("pkgNo01", pkgNo01);
      hashmap.put("pkgNo02", pkgNo02);
      hashmap.put("pkgNo03", pkgNo03);
      hashmap.put("pkgNo04", pkgNo04);
      return pkgService.getRecommendedList(hashmap);
    }

}
