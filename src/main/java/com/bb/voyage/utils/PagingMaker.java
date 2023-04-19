package com.bb.voyage.utils;

import lombok.Data;

@Data
public class PagingMaker {

  private int total; // 전체 게시물 수
  private int pageTotal; // 전체 페이지 수
  private int startPage; // 하단표시 첫번째 페이지 번호
  private int endPage; // 하단표시 마지막 페이지 번호
  private boolean prev;
  private boolean next;
  private int prevPage;
  private int nextPage;

  public void setTotalCount(int total, int page) {
    this.total = total;
    this.pageTotal = (int)Math.ceil((total-1)/10)+1;
    if(pageTotal<5){
      this.startPage = 1;
      this.endPage = pageTotal;
    }else{
    if(page<3){
      this.startPage = 1;
      this.endPage = 5;
      this.prev = false;
      this.next = true;
      this.nextPage = endPage+3;
    } else if(page>pageTotal-3){
      this.startPage = page-2;
      this.endPage = pageTotal; 
      this.prev = true;
      this.next = false;
      this.prevPage = startPage-3;
    } else{
      this.startPage = page-2;
      this.endPage = page+2; 
      this.prev = true;
      this.next = true;
      this.prevPage = startPage-3;
      this.nextPage = endPage+3;
      if(page==3){
        this.prev = false;
      }
      if(page==pageTotal-2){
        this.next = false;
      }
      if(page<6){
      this.prevPage = 1;
      }
      if(page>pageTotal-5){
        this.nextPage = pageTotal;
      }
    }
  }
}
}