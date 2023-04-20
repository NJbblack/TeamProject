package com.bb.voyage.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int reviewNo;
    private int pkgNo;
    private int reservNo;
    private int memberNo;
    private String reviewTitle;
    private String reviewContents;
    private String reviewDate;
    private int ratingGS; 
    private int ratingFA; 
    private int ratingCE; 
    private double ratingAVG;
    private String ratingAVGTxt;
    private String pkgName;
    private String reviewName; // DB에 안 들어감
}
