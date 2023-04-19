package com.bb.voyage.service;

import java.util.List;

import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.dto.RequestDto;

public interface ReservService {
    public List<ReservDto> getAllReserv(int no);
    public ReservDto getOneReserv(int reservNo);
    public List<ReservDto> getPastReserv(int no); 
    public List<ReservDto> getCanceledReserv(int no); 
    public List<RequestDto> getAllRequest(int no); 
    public List<RequestDto> getClosedRequest(int no); 
    public RequestDto getOneRequest(int reqNo);
    public ReservDto getDetailReserv(int reservNo);
    public PkgDto getDetailPkg(int reservNo);
    public MemberDto getDetailMember(int reservNo);
    public int reservProcess(ReservDto reservDto);    
    public List<PkgDto> getFilteredPkg(PkgDto pkgDto);
    public int reservationModifyProcess(ReservDto reservDto);
    public int requestProcess(RequestDto requestDto);
    public int requestModifyProcess(RequestDto requestDto);
    public ReservDto reqGetReserv(int reqNo);  
    public PkgDto reqGetPkg(int reservNo);  
    public int reviewProcess(ReviewDto reviewDto);
    public int updateReviewAvailable(int reservNo);

}
