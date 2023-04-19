package com.bb.voyage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
  @Autowired
  MemberService memberService;

/////////////////////////////////////////////////////////////////////
////Member 기능 관련 컨트롤러
  @GetMapping("/memberinfo")
  public String memberInfo() {
    return "/member/memberinfo";
  }

  @GetMapping("/memberreservation")
  public String memberreservation(){
    return "/member/memberreservation";
  }

  @GetMapping("/memberrequest")
  public String memberrequest(){
    return "/member/memberrequest";
  }

  @PostMapping("/memberAlert")
  @ResponseBody
  public Map<String, Object> getMemberInfo(int userNo) {
    int result = memberService.getMemberInfo(userNo).getUserAlert();
    Map<String, Object> sendJson = new HashMap<>();
    switch (result){
      case 10: sendJson.put("msg","Your new Reservation is now secured!"); break;
      case 11: sendJson.put("msg","Your Reservation is now updated!"); break;
      case 20: sendJson.put("msg","Your new Request was sent successfully!"); break;
      case 21: sendJson.put("msg","Your Request is now updated!"); break;
      case 22: sendJson.put("msg","Your Request is now closed!"); break;
      case 90: sendJson.put("msg","A New Request has arrived!"); break;
      default: return null;
    }
    return sendJson;
  }

  @PostMapping("/memberAlertClear")
  public int memberAlertClear(int userNo){
    int result = memberService.clearUserAlert(userNo);
    return result;
  }
  

/////////////////////////////////////////////////////////////////////
////Member 관련 컨트롤러
  @GetMapping("/memberjoin")
  public String memberjoin() {
    return "/member/memberjoin";
  }


  @PostMapping("/memberJoinProcess")
  public String memberJoinProcess(MemberDto memberDto,
  String userZipcode,
  String userAddr01,
  String userAddr02){
    String userAddress = userZipcode + " " + userAddr01 + " " + userAddr02;
    memberDto.setAddress(userAddress);
    memberService.memberJoinProcess(memberDto);
    return "redirect:/member/memberlogin";
  }

  @PostMapping("/idCheck")
  @ResponseBody
  public int idCheck(MemberDto memberDto, String userId)   {
    int result = memberService.idCheck(memberDto);
    return result;
  }

  @GetMapping("/memberlogin")
  public String memberlogin() {
    return "/member/memberlogin";
  }

  @PostMapping("/memberLoginProcess")
  public String memberLoginProcess(MemberDto memberDto, HttpServletRequest request){
    MemberDto loggedMember = memberService.memberLoginProcess(memberDto);
    if (loggedMember == null) {
      return "redirect:/member/memberlogin";
    } else {
      HttpSession session = request.getSession();
      session.setAttribute("loggedMember", loggedMember);
      return "redirect:/member/memberinfo";
    }
  }

  @GetMapping("/memberlogout")
  public String memberLogout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.removeAttribute("loggedMember");
    return "redirect:/";
  }

  @GetMapping("/membermodify")
  public String memberModify(){
  return "/member/membermodify";
  }

  @PostMapping("/memberModifyProcess")
  public String memberModifyProcess(MemberDto memberDto, HttpServletRequest request, HttpServletResponse response){
    memberService.memberModifyProcess(memberDto);
    MemberDto loggedMember = memberService.memberLoginProcess(memberDto);
    HttpSession session = request.getSession();
    session.setAttribute("loggedMember", loggedMember);
    return "redirect:/member/memberinfo";
  }

  @PostMapping("/memberDeleteProcess")
  @ResponseBody
  public Map<String, Object> memberDeleteProcess(MemberDto memberDto, HttpServletRequest request){
    int result = memberService.memberDeleteProcess(memberDto);
    Map<String, Object> sendJson = new HashMap<>();
    if (result > 0) {
      sendJson.put("msg", "ok");
      HttpSession session = request.getSession();
      session.removeAttribute("loggedMember");
    } else {
      sendJson.put("msg", "fail");
    }
    return sendJson;
  }



}
