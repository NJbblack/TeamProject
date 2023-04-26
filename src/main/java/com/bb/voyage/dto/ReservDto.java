package com.bb.voyage.dto;

import lombok.Data;

@Data
public class ReservDto {
    int reservNo;
    int customerNo;
    int selectedPkgNo;
    String reservName;
    String reservBday;
    String reservPassport;
    String reservEmail;
    String reservRegDate;
    String reservPkg;
    String reservImg;
    String reservDepart;
    String reservReturn;
    int reservAvailable;
    int reservReq;
    int reservReqNo;
    int reviewAvailable;
    String pkgContents; // DB에는 들어가지 않음
    private String ipAddress; // DB에는 들어가지 않음

}


