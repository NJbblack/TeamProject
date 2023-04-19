package com.bb.voyage.dto;

import lombok.Data;

@Data
public class RequestDto {
private int reqNo;
private int reservNo;
private String reqCategory;
private String reqTitle;
private String reqContents;
private int reqNow;
private int reqUserNo;
private int pkgNo;
private String pkgName;
private String pkgDepart;
private String pkgReturn;
private String requestImg;
private String reservName;
private String reqDate;
}
