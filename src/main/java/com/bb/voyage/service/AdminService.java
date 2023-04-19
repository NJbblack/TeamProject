package com.bb.voyage.service;

import java.util.List;

import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.PkgSubImgDto;
import com.bb.voyage.dto.RequestDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;

public interface AdminService {
  // =================Admin Package 관련
    public List<PkgDto> getAllPkg(int page, String searchTxt, String category);
    public PkgDto getOnePkg(int pkgNo);
    public PkgSubImgDto getOnePkgSubImg(int pkgNo);
    public int totalCountPkg();   

    public int pkgInsertProcess(PkgDto pkgDto);
    public int pkgModifyProcess(PkgDto pkgDto);
    public int pkgModifyImgProcess(PkgDto pkgDto);
    public int pkgDeleteProcess(int pkgNo);
    public int pkgAddSubImgProcess(PkgSubImgDto pkgSubImgDto);
    public int pkgAlterSubImgProcess(PkgSubImgDto pkgSubImgDto);



  // =================Admin Reservation 관련
    public List<ReservDto> getAllReserv(int page, String searchTxt, String category, int requested);
    public ReservDto getOneReserv(int reservNo);
    public int totalCountReserv();     

    public int reservModifyProcess(ReservDto reservDto);
    public int reservDeleteProcess(int reservNo);



  // =================Admin Member 관련
    public List<MemberDto> getAllMember(int page, String searchTxt, String category);
    public MemberDto getOneMember(int no);
    public int totalCountMember();

    public int memberModifyProcess(MemberDto memberDto);
    public int memberDeleteProcess(int no);



  // =================Request 관련
    public MemberDto reservRequestView(int customerNo);
    public RequestDto getOneRequest(int reqNo);



  // =================Review 관련
    public List<ReviewDto> getAllReview(int page, String searchTxt, String category);
    public ReviewDto getOneReview(int reservNo);
    public int reviewModifyProcess(ReviewDto reviewDto);

}
