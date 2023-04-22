package com.bb.voyage.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.dto.RequestDto;

@Mapper
public interface ReservDao {
  // =================Reservation관련
  public List<ReservDto> getAllReserv(int no);
  public ReservDto getOneReserv(int reservNo);
  public List<ReservDto> getPastReserv(int no); 
  public List<ReservDto> getCanceledReserv(int no); 
  public ReservDto getDetailReserv(int reservNo);


  public int reservProcess(ReservDto reservDto);
  public int reservationModifyProcess(ReservDto reservDto);



  // =================Request관련
  public List<RequestDto> getAllRequest(int no); 
  public RequestDto getOneRequest(int reqNo);
  public List<RequestDto> getClosedRequest(int no); 
  public List<RequestDto> getAllRequestFor(int reservNo);
  public ReservDto reqGetReserv(int reservNo);  
  public PkgDto reqGetPkg(int reservNo);  

  public int requestProcess(RequestDto requestDto);
  public int requesttoReserv(RequestDto requestDto);
  public int requestModifyProcess(RequestDto requestDto);


  
  // =================기타
  public List<PkgDto> getFilteredPkg(PkgDto pkgDto);
  public List<PkgDto> getLinkedPkg(PkgDto pkgDto);
  public PkgDto getOnePkg(int selectedPkgNo);
  public PkgDto getDetailPkg(int reservNo);
  public MemberDto getDetailMember(int reservNo);



  // =================Review 관련
  public int reviewProcess(ReviewDto reviewDto);
  public int updateReviewAvailable(int reservNo);
  public List<ReviewDto> getUserReview(int no); 

}
