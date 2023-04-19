package com.bb.voyage.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class ScriptWriter {

  // 경고창만 띄우기
  public static void alert(HttpServletResponse response, String alertMsg)
    throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    out.println("alert('" + alertMsg + "');");
    out.println("</script>");
  }

  // 경고창 띄우고 특정 페이지로 보내기
  public static void alertAndNext(
    HttpServletResponse response,
    String alertMsg,
    String next
  ) throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    out.println("alert('" + alertMsg + "');");
    out.println("location.href='" + next + "';");
    out.println("</script>");
  }

  // 경고창 띄우고 특정 페이지로 보내기
  public static void alertAndBack(
    HttpServletResponse response,
    String alertMsg
  ) throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    out.println("alert('" + alertMsg + "');");
    out.println("history.back();");
    out.println("</script>");
  }
}
