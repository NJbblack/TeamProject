package com.bb.voyage.dto;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PkgDto {
    private int pkgNo;
    private String pkgName;
    private String pkgContents;
    private String pkgRegion;
    private String pkgDepart;
    private String pkgReturn;
    private int pkgBudget;
    private int minBudget; // DB에는 들어가지 않음
    private int maxBudget; // DB에는 들어가지 않음
    private int pkgAvailable; 
    private MultipartFile pkgMainOrigin; // DB에는 들어가지 않음
    private String pkgMainOriginPath;
    private String pkgMainRenamedPath;
    private int pkgSubImgNo;
    private double ratedGS;
    private double ratedFA;
    private double ratedCE; 
    private int ratedCount;
    private double ratedAVG;
    private String ratedAVGTxt;
    private double ratedStar;
}
