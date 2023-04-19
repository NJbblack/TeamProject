package com.bb.voyage.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.PkgSubImgDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;

@Mapper
public interface AdminDao {
  // =================Admin Package 관련
  public List<PkgDto> getAllPkg(HashMap<String, Object> hashMap);
  public PkgDto getOnePkg(int pkgNo);
  public PkgSubImgDto getOnePkgSubImg(int pkgNo);
  public int totalCountPkg();    

  public int pkgInsertProcess(PkgDto pkgDto);
  public int pkgModifyProcess(PkgDto pkgDto);
  public int pkgModifyReservProcess(PkgDto pkgDto);
  public int pkgModifyImgProcess(PkgDto pkgDto);
  public int pkgDeleteProcess(int pkgNo);
  public int pkgAddSubImgProcess(PkgSubImgDto pkgSubImgDto);
  public int UpdateSubImgNoToPkg(PkgSubImgDto pkgSubImgDto);
  public int pkgAlterSubImgProcess(PkgSubImgDto pkgSubImgDto);



  // =================Admin Reservation 관련
  public List<ReservDto> getAllReserv(HashMap<String, Object> hashMap);
  public ReservDto getOneReserv(int reservNo);
  public int totalCountReserv();    

  public int reservModifyProcess(ReservDto reservDto);
  public int reservDeleteProcess(int reservNo);
  public int updateReq(ReservDto reservDto);


  
  // =================Admin Member 관련
  public List<MemberDto> getAllMember(HashMap<String, Object> hashMap);
  public MemberDto getOneMember(int no);
  public int totalCountMember();

  public int memberModifyProcess(MemberDto memberDto);
  public int memberDeleteProcess(int no);



  // =================Review 관련
  public List<ReviewDto> getAllReview(HashMap<String, Object> hashMap);
  public ReviewDto getOneReview(int reservNo);
  public int reviewModifyProcess(ReviewDto reviewDto);
  
}


