package com.bb.voyage.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bb.voyage.service.BoardService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class HitChecker {

  @Autowired
  BoardService boardService;
    
  public void faqHitChecker(int no, HttpServletRequest request, HttpServletResponse response, BoardService boardService) {
    log.info("결과:{}",no);
    Cookie[] cookies = request.getCookies();
    if (cookies == null){
      Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
      newCookie.setMaxAge(60 * 60 * 10);
      response.addCookie(newCookie);
      int result = boardService.updateFaqHit(no);
      log.info("result: {}",result);
      
    } else{
      for (Cookie cookie : cookies) {
        if((!cookie.getValue().contains(request.getParameter("no")))&&(cookie.getName().equals("visit_cookie"))){
          Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
          newCookie.setValue(newCookie.getValue() + "_" + request.getParameter("no"));
          newCookie.setMaxAge(60 * 60 * 10);
          response.addCookie(newCookie);
          int result = boardService.updateFaqHit(no);
          log.info("result: {}",result);
        }
      }
    }    
}

  public void noticeHitChecker(int no, HttpServletRequest request, HttpServletResponse response, BoardService boardService) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null){
      Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
      newCookie.setMaxAge(60 * 60 * 10);
      response.addCookie(newCookie);
      boardService.updateNoticeHit(no);
    } else{
      for (Cookie cookie : cookies) {
        if((!cookie.getValue().contains(request.getParameter("no")))&&(cookie.getName().equals("visit_cookie"))){
          Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
          newCookie.setValue(newCookie.getValue() + "_" + request.getParameter("no"));
          newCookie.setMaxAge(60 * 60 * 10);
          response.addCookie(newCookie);
          boardService.updateNoticeHit(no);
        }
      }
    }
}

public void qnaHitChecker(int no, HttpServletRequest request, HttpServletResponse response, BoardService boardService) {
  Cookie[] cookies = request.getCookies();
  if (cookies == null){
    Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
    newCookie.setMaxAge(60 * 60 * 10);
    response.addCookie(newCookie);
    int result = boardService.updateQnaHit(no);
    log.info("result: {}",result);
    
  } else{
    for (Cookie cookie : cookies) {
      if((!cookie.getValue().contains(request.getParameter("no")))&&(cookie.getName().equals("visit_cookie"))){
        Cookie newCookie = new Cookie("visit_cookie", request.getParameter("no"));
        newCookie.setValue(newCookie.getValue() + "_" + request.getParameter("no"));
        newCookie.setMaxAge(60 * 60 * 10);
        response.addCookie(newCookie);
        int result = boardService.updateQnaHit(no);
        log.info("result: {}",result);
      }
    }
  }    
}
}