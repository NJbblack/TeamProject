package com.bb.voyage.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PkgSubImgDto {
    int pkgSubNo;
    int PkgNo;
    MultipartFile originSubImg01;
    MultipartFile originSubImg02;
    MultipartFile originSubImg03;
    MultipartFile originSubImg04;
    MultipartFile originSubImg05;
    MultipartFile originSubImg06;
    MultipartFile originSubImg07;
    MultipartFile originSubImg08;
    String subImg01;
    String subImg02;
    String subImg03;
    String subImg04;
    String subImg05;
    String subImg06;
    String subImg07;
    String subImg08;
}
