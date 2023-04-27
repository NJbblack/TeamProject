package com.bb.voyage.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import com.bb.voyage.service.MemberService;
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
    @Autowired
    MemberService memberService;

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

      for(ReviewDto item : reviewList){
      item.setReviewName(memberService.getMemberInfo(item.getMemberNo()).getUserNickname()); 
      }
      model.addAttribute("pkgDto", pkgDto);
      model.addAttribute("pkgSubImgDto", pkgSubImgDto);
      model.addAttribute("reviewList", reviewList);
      return "/package/pkgdetailview";
    }

    @PostMapping("/pkgdetailview")
    @ResponseBody
    public PkgDto pkgViewImg02(int pkgNo, Model model) throws UnknownHostException{
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      String ipAddress = InetAddress.getLocalHost().getHostAddress();
      // String ipAddress = "221.139.100.224";

      pkgDto.setIpAddress(ipAddress);
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
    public List<PkgDto> getTempRecoList(PkgDto pkgDto) throws UnknownHostException{
      List<PkgDto> pkgList = pkgService.getTempRecoList(pkgDto);
      String ipAddress = InetAddress.getLocalHost().getHostAddress();
      // String ipAddress = "221.139.100.224";

      for(PkgDto item : pkgList){
        item.setIpAddress(ipAddress);
      }
      return pkgList;
    }

    @PostMapping("/getRecommendedList")
    @ResponseBody
    public List<PkgDto> getRecommendedList(int pkgNo01, int pkgNo02, int pkgNo03, int pkgNo04) throws UnknownHostException{
      HashMap<String,Object> hashmap = new HashMap<>();
      hashmap.put("pkgNo01", pkgNo01);
      hashmap.put("pkgNo02", pkgNo02);
      hashmap.put("pkgNo03", pkgNo03);
      hashmap.put("pkgNo04", pkgNo04);
      log.info("결과: {}",hashmap);
      List<PkgDto> recommendedList = pkgService.getRecommendedList(hashmap);
      String ipAddress = InetAddress.getLocalHost().getHostAddress();
      // String ipAddress = "221.139.100.224";

      for(PkgDto item : recommendedList){
        item.setIpAddress(ipAddress);
      }
      return recommendedList;
    }

}
