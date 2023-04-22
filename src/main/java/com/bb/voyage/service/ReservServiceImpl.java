package com.bb.voyage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bb.voyage.dao.PkgDao;
import com.bb.voyage.dao.ReservDao;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.dto.RequestDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReservServiceImpl implements ReservService{

    @Autowired
    ReservDao reservDao;
    @Autowired
    MemberService memberService;
    @Autowired
    PkgDao pkgDao;

/////////////////////////////////////////////////////////////////////
////Reservation 관련 서비스
    public List<ReservDto> getAllReserv(int no) {
        List<ReservDto> reservList = reservDao.getAllReserv(no);
        for(ReservDto item : reservList){
          item.setPkgContents(reservDao.getDetailPkg(item.getReservNo()).getPkgContents());
        }
        return reservList;
    }

    public List<ReservDto> getPastReserv(int no) {
        List<ReservDto> reservList = reservDao.getPastReserv(no);
        for(ReservDto item : reservList){
          item.setPkgContents(reservDao.getDetailPkg(item.getReservNo()).getPkgContents());
        }
        return reservList;
    }

    public List<ReservDto> getCanceledReserv(int no) {
        List<ReservDto> reservList = reservDao.getCanceledReserv(no);
        for(ReservDto item : reservList){
          item.setPkgContents(reservDao.getDetailPkg(item.getReservNo()).getPkgContents());
        }
        return reservList;
    }

    public ReservDto getOneReserv(int reservNo) {
        return reservDao.getOneReserv(reservNo);
    }



    public ReservDto getDetailReserv(int reservNo) {
        return reservDao.getDetailReserv(reservNo);
    }

    public PkgDto getDetailPkg(int reservNo) {
        return reservDao.getDetailPkg(reservNo);
    }

    public MemberDto getDetailMember(int reservNo) {
        return reservDao.getDetailMember(reservNo);
    }

    public int reservProcess(ReservDto reservDto) {
        int selectedPkgNo = reservDto.getSelectedPkgNo();
        PkgDto pkgDto = reservDao.getOnePkg(selectedPkgNo);
        reservDto.setReservPkg(pkgDto.getPkgName());
        reservDto.setReservImg(pkgDto.getPkgMainRenamedPath());
        reservDto.setReservDepart(pkgDto.getPkgDepart());
        reservDto.setReservReturn(pkgDto.getPkgReturn());
        memberService.updateUserAlert(reservDto.getCustomerNo(),10);
        return reservDao.reservProcess(reservDto);
    }

    public List<PkgDto> getFilteredPkg(PkgDto pkgDto) {
        if(pkgDto.getPkgName() != null){
            return reservDao.getLinkedPkg(pkgDto);
        } else{
            return reservDao.getFilteredPkg(pkgDto);
        }


    }

    public int reservationModifyProcess(ReservDto reservDto) {
        memberService.updateUserAlert(reservDto.getCustomerNo(),11);
        return reservDao.reservationModifyProcess(reservDto);
    }



/////////////////////////////////////////////////////////////////////
////Request 관련 서비스
    public int requestProcess(RequestDto requestDto) {
        List<RequestDto> requestList = reservDao.getAllRequestFor(requestDto.getReservNo());
        int totalNow = 0;
        for(RequestDto i : requestList){
            totalNow += i.getReqNow();
        }
        if(totalNow>0){
            return 0;
        } else{
            int result = reservDao.requestProcess(requestDto);
            log.info("결과: {}",result);
            reservDao.requesttoReserv(requestDto);
            memberService.updateUserAlert(requestDto.getReqUserNo(),20);
            memberService.updateAdminAlert(90);
            return result;
        }
    }

    public List<RequestDto> getAllRequest(int no) {
        List<RequestDto> requestList = reservDao.getAllRequest(no);
        for(RequestDto item : requestList){
          item.setPkgNo(reservDao.reqGetPkg(item.getReservNo()).getPkgNo()); 
          item.setPkgName(reservDao.reqGetPkg(item.getReservNo()).getPkgName()); 
          item.setPkgDepart(reservDao.reqGetPkg(item.getReservNo()).getPkgDepart()); 
          item.setPkgReturn(reservDao.reqGetPkg(item.getReservNo()).getPkgReturn()); 
          item.setRequestImg(reservDao.reqGetPkg(item.getReservNo()).getPkgMainRenamedPath()); 
          item.setReservName(reservDao.reqGetReserv(item.getReservNo()).getReservName());
        }
        return requestList;
    }

    public List<RequestDto> getClosedRequest(int no) {
        List<RequestDto> requestList = reservDao.getClosedRequest(no);
        for(RequestDto item : requestList){
          item.setPkgNo(reservDao.reqGetPkg(item.getReservNo()).getPkgNo()); 
          item.setPkgName(reservDao.reqGetPkg(item.getReservNo()).getPkgName()); 
          item.setPkgDepart(reservDao.reqGetPkg(item.getReservNo()).getPkgDepart()); 
          item.setPkgReturn(reservDao.reqGetPkg(item.getReservNo()).getPkgReturn()); 
          item.setRequestImg(reservDao.reqGetPkg(item.getReservNo()).getPkgMainRenamedPath()); 
          item.setReservName(reservDao.reqGetReserv(item.getReservNo()).getReservName());
        }
        return requestList;
    }

    public RequestDto getOneRequest(int reqNo) {
        return reservDao.getOneRequest(reqNo);
    }

    public ReservDto reqGetReserv(int reqNo){
        return reservDao.reqGetReserv(reqNo);
    }

    public PkgDto reqGetPkg(int reservNo){
        return reservDao.reqGetPkg(reservNo);
    }

    public int requestModifyProcess(RequestDto requestDto) {
        memberService.updateUserAlert(requestDto.getReqUserNo(),21);
        return reservDao.requestModifyProcess(requestDto);
    }



/////////////////////////////////////////////////////////////////////
////Review 관련 서비스
    public int reviewProcess(ReviewDto reviewDto) {
        double ratingAVG = Math.round(((reviewDto.getRatingCE()+reviewDto.getRatingFA()+reviewDto.getRatingGS())/3.0)*10)/10.0;
        reviewDto.setRatingAVG(ratingAVG);
        reviewDto.setRatingAVGTxt(""+ratingAVG);
        int result = reservDao.reviewProcess(reviewDto);
        
        PkgDto pkgDto = reservDao.reqGetPkg(reviewDto.getReservNo());
        double ratedGS = (double)((pkgDto.getRatedGS())*(pkgDto.getRatedCount())+reviewDto.getRatingGS())/(pkgDto.getRatedCount()+1);
        double ratedFA = (double)((pkgDto.getRatedFA())*(pkgDto.getRatedCount())+reviewDto.getRatingFA())/(pkgDto.getRatedCount()+1);
        double ratedCE = (double)((pkgDto.getRatedCE())*(pkgDto.getRatedCount())+reviewDto.getRatingCE())/(pkgDto.getRatedCount()+1);
        int ratedCount = (pkgDto.getRatedCount()+1);
        double ratedAvg = Math.round(((ratedGS+ratedFA+ratedCE)/3.0)*10)/10.0;
        pkgDto.setRatedAVG(ratedAvg);
        pkgDto.setRatedAVGTxt(""+ratedAvg);
        pkgDto.setRatedStar(Math.round(pkgDto.getRatedAVG()));
        pkgDto.setRatedGS(ratedGS);
        pkgDto.setRatedFA(ratedFA);
        pkgDto.setRatedCE(ratedCE);
        pkgDto.setRatedCount(ratedCount);
        pkgDao.pkgSetRating(pkgDto);
        reservDao.updateReviewAvailable(reviewDto.getReservNo());
        return result;
    }

    public int updateReviewAvailable(int reservNo) {
        int result = reservDao.updateReviewAvailable(reservNo);
        return result;
    }

    public List<ReviewDto> getUserReview(int no) {
        return reservDao.getUserReview(no);
    }


}
