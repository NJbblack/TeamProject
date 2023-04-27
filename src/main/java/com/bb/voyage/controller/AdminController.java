package com.bb.voyage.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.PkgSubImgDto;
import com.bb.voyage.dto.RequestDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;
import com.bb.voyage.service.AdminService;
import com.bb.voyage.service.PkgService;
import com.bb.voyage.service.ReservService;
import com.bb.voyage.utils.PagingMaker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    PkgService pkgService;
    @Autowired
    ReservService reservService;

    @GetMapping("/menu")
    public String menu(){
      return "/admin/menu";
    }
/////////////////////////////////////////////////////////////////////
////관리자용 Package 관리 컨트롤러
    @GetMapping("/pkglist")
    public String pkgList(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
      List<PkgDto> pkgList = adminService.getAllPkg(page, searchTxt, category);
      pagingMaker.setTotalCount(pkgList.size(), (int)page);
      model.addAttribute("pkgList", pkgList);
      model.addAttribute("pagingMaker", pagingMaker);
      return "/admin/pkglist";
    }
    
    @GetMapping("/pkgviewimg")
    public String pkgViewImg(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      PkgSubImgDto pkgSubImgDto = adminService.getOnePkgSubImg(pkgNo);
      model.addAttribute("pkgDto", pkgDto);
      model.addAttribute("pkgSubImgDto", pkgSubImgDto);
      return "/admin/pkgviewimg";
    }

    @GetMapping("/pkginsert")
    public String adminPkg(){
        return "/admin/pkginsert";
    }

    @PostMapping("/pkgInsertProcess")
    public String pkgInsertProcess(PkgDto pkgDto){
      adminService.pkgInsertProcess(pkgDto);
        return "redirect:/admin/pkglist";
    }

    @GetMapping("/pkgmodify")
    public String pkgmodify(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      model.addAttribute("pkgDto", pkgDto);
      return "/admin/pkgmodify";
    }

    @PostMapping("/pkgModifyProcess")
    public String pkgModifyProcess(PkgDto pkgDto){
      adminService.pkgModifyProcess(pkgDto);
      pkgService.pkgSetRating(pkgDto);
      return "redirect:/admin/pkglist";
    }

    @GetMapping("/pkgmodifyimg")
    public String pkgmodifyimg(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      model.addAttribute("pkgDto", pkgDto);
      return "/admin/pkgmodifyimg";
    }

    @PostMapping("/pkgModifyImgProcess")
    public String pkgModifyImgProcess(PkgDto pkgDto){
      adminService.pkgModifyImgProcess(pkgDto);
      return "redirect:/admin/pkglist";
    }

    @GetMapping("/pkgsubimg")
    public String pkgsubimg(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      model.addAttribute("pkgDto", pkgDto);
      return "/admin/pkgsubimg";
    }

    @GetMapping("/pkgmodifysubimg")
    public String pkgmodifysubimg(int pkgNo, Model model){
      PkgDto pkgDto = adminService.getOnePkg(pkgNo);
      model.addAttribute("pkgDto", pkgDto);
      return "/admin/pkgmodifysubimg";
    }

    @PostMapping("/pkgAddSubImgProcess")
    public String pkgAddSubImgProcess(PkgSubImgDto pkgSubImgDto){
      adminService.pkgAddSubImgProcess(pkgSubImgDto);
      return "redirect:/admin/pkglist";
    }
    
    @PostMapping("/pkgAlterSubImgProcess")
    public String pkgAlterSubImgProcess(PkgSubImgDto pkgSubImgDto){
      adminService.pkgAlterSubImgProcess(pkgSubImgDto);
      return "redirect:/admin/pkglist";
    }

    @GetMapping("/pkgDeleteProcess")
    public String pkgDeleteProcess(int pkgNo){
      adminService.pkgDeleteProcess(pkgNo);
      return "redirect:/admin/pkglist";
    }



/////////////////////////////////////////////////////////////////////
////관리자용 Reservation 관리 컨트롤러
    @GetMapping("/reservlist")
    public String reservList(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category, @RequestParam(value = "requested",defaultValue = "0")int requested){
      List<ReservDto> reservList = adminService.getAllReserv(page, searchTxt, category, (int)requested);
      pagingMaker.setTotalCount(reservList.size(), (int)page);
      log.info("페이지 메이커: {}",pagingMaker);
      model.addAttribute("reservList", reservList);
      model.addAttribute("pagingMaker", pagingMaker);
      return "/admin/reservlist";
    }

    @GetMapping("/reservmodify")
    public String reservModify(int reservNo, Model model){
      ReservDto reservDto = adminService.getOneReserv(reservNo);
      model.addAttribute("reservDto", reservDto);
      return "/admin/reservmodify";
    }

    @PostMapping("/reservModifyProcess")
    public String reservModifyProcess(ReservDto reservDto){
      log.info("이유: {}",reservDto);
      adminService.reservModifyProcess(reservDto);
      return "redirect:/admin/reservlist";
    }

    @GetMapping("/reservDeleteProcess")
    public String reservDeleteProcess(int reservNo){
      adminService.reservDeleteProcess(reservNo);
      return "redirect:/admin/reservlist";
    }

    @GetMapping("/reservrequestview")
    public String reservRequestView(int customerNo, int reservNo, Model model){
      MemberDto memberDto = adminService.reservRequestView(customerNo);
      ReservDto reservDto = adminService.getOneReserv(reservNo);
      RequestDto requestDto = adminService.getOneRequest(reservDto.getReservReqNo());
      PkgDto pkgDto = adminService.getOnePkg(reservDto.getSelectedPkgNo());
      model.addAttribute("reservDto", reservDto);
      model.addAttribute("memberDto", memberDto);
      model.addAttribute("requestDto", requestDto);
      model.addAttribute("pkgDto", pkgDto);
      return "/admin/reservrequestview";
    }


    
/////////////////////////////////////////////////////////////////////
////관리자용 Member 관리 컨트롤러
  @GetMapping("/memberlist")
  public String memberList(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
    List<MemberDto> memberList = adminService.getAllMember(page, searchTxt, category);
    pagingMaker.setTotalCount(memberList.size(), (int)page);
    model.addAttribute("memberList", memberList);
    model.addAttribute("pagingMaker", pagingMaker);
    return "/admin/memberlist";
  }

  @GetMapping("/membermodify")
  public String memberModify(Model model, int no){
    MemberDto memberDto = adminService.getOneMember(no);
    model.addAttribute("memberDto", memberDto);
    return "/admin/membermodify";
  }

  @PostMapping("/memberModifyProcess")
  public String memberModifyProcess(MemberDto memberDto){
    adminService.memberModifyProcess(memberDto);
    return "redirect:/admin/memberlist";
  }

  @GetMapping("/memberDeleteProcess")
  public String memberDeleteProcess(int no){
    adminService.memberDeleteProcess(no);
    return "redirect:/admin/memberlist";
  }

  

/////////////////////////////////////////////////////////////////////
////관리자용 Review 관리 컨트롤러
@GetMapping("/reviewlist")
public String reviewList(Model model, PagingMaker pagingMaker, @RequestParam(value = "page",defaultValue = "1")int page, String searchTxt, String category){
  List<ReviewDto> reviewList = adminService.getAllReview(page, searchTxt, category);
  pagingMaker.setTotalCount(reviewList.size(), (int)page);
  log.info("결과: {}",reviewList);
  model.addAttribute("reviewList", reviewList);
  model.addAttribute("pagingMaker", pagingMaker);
  return "/admin/reviewlist";
}

@GetMapping("/reviewmodify")
public String reviewmodify(int reviewNo, Model model){
  ReviewDto reviewDto = adminService.getOneReview(reviewNo);
  model.addAttribute("reviewDto", reviewDto);
  return "/admin/reviewmodify";
}

@PostMapping("/reviewModifyProcess")
public String reviewModifyProcess(ReviewDto reviewDto){
  adminService.reviewModifyProcess(reviewDto);
  return "redirect:/admin/reviewlist";
}
}
