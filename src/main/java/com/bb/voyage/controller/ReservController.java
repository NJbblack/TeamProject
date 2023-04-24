package com.bb.voyage.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.dto.RequestDto;
import com.bb.voyage.service.PkgService;
import com.bb.voyage.service.ReservService;
import com.bb.voyage.utils.AvgCalculater;

@Controller
@RequestMapping("/reservation")
public class ReservController {
    @Autowired
    ReservService reservService;
    @Autowired
    PkgService pkgService;

/////////////////////////////////////////////////////////////////////
////Reservation 관련 컨트롤러
    @GetMapping("/reservation")
    public String reservation(PkgDto pkgDto){
        return "/reservation/reservation";
    }

    @PostMapping("/getAllReserv")
    @ResponseBody
    public List<ReservDto> getAllReserv(int no){
        return reservService.getAllReserv(no);
    }

    @PostMapping("/getPastReserv")
    @ResponseBody
    public List<ReservDto> getPastReserv(int no){
        return reservService.getPastReserv(no);
    }

    @PostMapping("/getCanceledReserv")
    @ResponseBody
    public List<ReservDto> getCanceledReserv(int no){
        return reservService.getCanceledReserv(no);
    }

    @PostMapping("/getDetailReserv")
    public String getDetailReserv(int reservNo, Model model){
      MemberDto memberDto = reservService.getDetailMember(reservNo);
      PkgDto pkgDto = reservService.getDetailPkg(reservNo);
      ReservDto reservDto = reservService.getDetailReserv(reservNo);
        model.addAttribute(memberDto);
        model.addAttribute(pkgDto);
        model.addAttribute(reservDto);
        return "/reservation/reservationview";
    }

    @PostMapping("/reservProcess")
    public String reservProcess(ReservDto reservDto){
        reservService.reservProcess(reservDto);
        return "redirect:/member/memberreservation";
    }

    @PostMapping("/getFilteredPkg")
    @ResponseBody
    public List<PkgDto> getFilteredPkg(PkgDto pkgDto){
      List<PkgDto> pkgList = reservService.getFilteredPkg(pkgDto);
        return pkgList;
    }
    
    @GetMapping("/reservationmodify")
    public String reservationModify(int reservNo, Model model){
      ReservDto reservDto = reservService.getOneReserv(reservNo);
      model.addAttribute("reservDto", reservDto);
      return "/reservation/reservationmodify";
    }

    @PostMapping("/reservationModifyProcess")
    public String reservationModifyProcess(ReservDto reservDto){
      reservService.reservationModifyProcess(reservDto);
      return "redirect:/member/memberreservation";
    }



/////////////////////////////////////////////////////////////////////
////Request 관련 컨트롤러
    @GetMapping("/request")
    public String request(int reservNo, Model model){
      ReservDto reservDto = reservService.getOneReserv(reservNo);
      model.addAttribute("reservDto", reservDto);
      return "/reservation/request";
    }
    
    @PostMapping("/requestview")
    public String requestview(int reqNo, int reservNo, Model model){
      RequestDto requestDto = reservService.getOneRequest(reqNo);
      ReservDto reservDto = reservService.reqGetReserv(reqNo);
      PkgDto pkgDto = reservService.reqGetPkg(reservNo);
      model.addAttribute("requestDto", requestDto);
      model.addAttribute("reservDto", reservDto);
      model.addAttribute("pkgDto", pkgDto);
      return "/reservation/requestview";
    }

    @PostMapping("/requestProcess")
    public String requestProcess(RequestDto requestDto){
      reservService.requestProcess(requestDto);
        return "redirect:/member/memberrequest";
    }


    @PostMapping("/requestModifyProcess")
    public String requestModifyProcess(RequestDto requestDto){
      reservService.requestModifyProcess(requestDto);
      return "redirect:/member/memberrequest";
    }
   
    @PostMapping("/getAllRequest")
    @ResponseBody
    public List<RequestDto> getAllRequest(int no){
        return reservService.getAllRequest(no);
    }

    @PostMapping("/getClosedRequest")
    @ResponseBody
    public List<RequestDto> getClosedRequest(int no){
        return reservService.getClosedRequest(no);
    }



/////////////////////////////////////////////////////////////////////
////Review 관련 컨트롤러
    @GetMapping("/review")
    public String review(int reservNo, int selectedPkgNo, Model model){
      ReservDto reservDto = reservService.getOneReserv(reservNo);
      PkgDto pkgDto = reservService.reqGetPkg(reservNo);
      model.addAttribute("reservDto", reservDto);
      model.addAttribute("pkgDto", pkgDto);
      return "/reservation/review";
    }

    @PostMapping("/reviewProcess")
    public String reviewProcess(ReviewDto reviewDto, AvgCalculater avgCalculater){
      reservService.reviewProcess(reviewDto, avgCalculater);
      return "redirect:/member/memberreview";
    }

    @PostMapping("/getUserReview")
    @ResponseBody
    public List<ReviewDto> getUserReview(int no){
        return reservService.getUserReview(no);
    }
}
