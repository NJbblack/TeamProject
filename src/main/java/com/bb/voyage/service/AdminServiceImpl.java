package com.bb.voyage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bb.voyage.dao.AdminDao;
import com.bb.voyage.dao.MemberDao;
import com.bb.voyage.dao.PkgDao;
import com.bb.voyage.dao.ReservDao;
import com.bb.voyage.dto.MemberDto;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.PkgSubImgDto;
import com.bb.voyage.dto.RequestDto;
import com.bb.voyage.dto.ReservDto;
import com.bb.voyage.dto.ReviewDto;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    ReservDao reservDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    MemberService memberService;
    @Autowired
    PkgDao pkgDao;
    @Value("${file.path}")
    private String uploadFolder;

/////////////////////////////////////////////////////////////////////
////관리자용 Package 관리 서비스
    public List<PkgDto> getAllPkg(int page, String searchTxt, String category) {
      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("page", page);
      hashMap.put("searchTxt", searchTxt);
      hashMap.put("category", category);
      return adminDao.getAllPkg(hashMap);
    }

    public PkgDto getOnePkg(int pkgNo) {
      return adminDao.getOnePkg(pkgNo);
    }

    public PkgSubImgDto getOnePkgSubImg(int pkgNo){
      return adminDao.getOnePkgSubImg(pkgNo);
    }

    public int totalCountPkg() {
      return adminDao.totalCountPkg();
    }

    public int pkgInsertProcess(PkgDto pkgDto) {
      UUID uuid = UUID.randomUUID();
      String pkgMainOrigin = pkgDto.getPkgMainOrigin().getOriginalFilename();
      String renameFile = uuid + "_" + pkgMainOrigin;
      Path imgFilePath = Paths.get(uploadFolder + renameFile); 
    
      pkgDto.setPkgMainOriginPath(pkgMainOrigin);
      pkgDto.setPkgMainRenamedPath(renameFile);
    
      try {
        Files.write(imgFilePath, pkgDto.getPkgMainOrigin().getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
      int result = adminDao.pkgInsertProcess(pkgDto);
      return result;
    }

    public int pkgModifyProcess(PkgDto pkgDto) {
      int result = adminDao.pkgModifyProcess(pkgDto);
      adminDao.pkgModifyReservProcess(pkgDto);
      return result;
    }

    public int pkgModifyImgProcess(PkgDto pkgDto) {
    UUID uuid = UUID.randomUUID();
    String pkgMainOrigin = pkgDto.getPkgMainOrigin().getOriginalFilename();
    String renameFile = uuid + "_" + pkgMainOrigin;
    Path imgFilePath = Paths.get(uploadFolder + renameFile); 
  
    pkgDto.setPkgMainOriginPath(pkgMainOrigin);
    pkgDto.setPkgMainRenamedPath(renameFile);
  
    try {
      Files.write(imgFilePath, pkgDto.getPkgMainOrigin().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    int result = adminDao.pkgModifyImgProcess(pkgDto);
    return result;
    }

    public int pkgAddSubImgProcess(PkgSubImgDto pkgSubImgDto) {
      UUID uuid = UUID.randomUUID();
      String subImg01 = uuid + "_" + pkgSubImgDto.getOriginSubImg01().getOriginalFilename();
      String subImg02 = uuid + "_" + pkgSubImgDto.getOriginSubImg02().getOriginalFilename();
      String subImg03 = uuid + "_" + pkgSubImgDto.getOriginSubImg03().getOriginalFilename();
      String subImg04 = uuid + "_" + pkgSubImgDto.getOriginSubImg04().getOriginalFilename();
      String subImg05 = uuid + "_" + pkgSubImgDto.getOriginSubImg05().getOriginalFilename();
      String subImg06 = uuid + "_" + pkgSubImgDto.getOriginSubImg06().getOriginalFilename();
      String subImg07 = uuid + "_" + pkgSubImgDto.getOriginSubImg07().getOriginalFilename();
      String subImg08 = uuid + "_" + pkgSubImgDto.getOriginSubImg08().getOriginalFilename();
      pkgSubImgDto.setSubImg01(subImg01);
      pkgSubImgDto.setSubImg02(subImg02);
      pkgSubImgDto.setSubImg03(subImg03);
      pkgSubImgDto.setSubImg04(subImg04);
      pkgSubImgDto.setSubImg05(subImg05);
      pkgSubImgDto.setSubImg06(subImg06);
      pkgSubImgDto.setSubImg07(subImg07);
      pkgSubImgDto.setSubImg08(subImg08);

      try {
        Files.write(Paths.get(uploadFolder + subImg01), pkgSubImgDto.getOriginSubImg01().getBytes());
        Files.write(Paths.get(uploadFolder + subImg02), pkgSubImgDto.getOriginSubImg02().getBytes());
        Files.write(Paths.get(uploadFolder + subImg03), pkgSubImgDto.getOriginSubImg03().getBytes());
        Files.write(Paths.get(uploadFolder + subImg04), pkgSubImgDto.getOriginSubImg04().getBytes());
        Files.write(Paths.get(uploadFolder + subImg05), pkgSubImgDto.getOriginSubImg05().getBytes());
        Files.write(Paths.get(uploadFolder + subImg06), pkgSubImgDto.getOriginSubImg06().getBytes());
        Files.write(Paths.get(uploadFolder + subImg07), pkgSubImgDto.getOriginSubImg07().getBytes());
        Files.write(Paths.get(uploadFolder + subImg08), pkgSubImgDto.getOriginSubImg08().getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
      adminDao.pkgAddSubImgProcess(pkgSubImgDto);
      int result = adminDao.UpdateSubImgNoToPkg(pkgSubImgDto);
      return result;
    }



    @Override
    public int pkgAlterSubImgProcess(PkgSubImgDto pkgSubImgDto) {
      UUID uuid = UUID.randomUUID();
      String subImg01 = uuid + "_" + pkgSubImgDto.getOriginSubImg01().getOriginalFilename();
      String subImg02 = uuid + "_" + pkgSubImgDto.getOriginSubImg02().getOriginalFilename();
      String subImg03 = uuid + "_" + pkgSubImgDto.getOriginSubImg03().getOriginalFilename();
      String subImg04 = uuid + "_" + pkgSubImgDto.getOriginSubImg04().getOriginalFilename();
      String subImg05 = uuid + "_" + pkgSubImgDto.getOriginSubImg05().getOriginalFilename();
      String subImg06 = uuid + "_" + pkgSubImgDto.getOriginSubImg06().getOriginalFilename();
      String subImg07 = uuid + "_" + pkgSubImgDto.getOriginSubImg07().getOriginalFilename();
      String subImg08 = uuid + "_" + pkgSubImgDto.getOriginSubImg08().getOriginalFilename();
      pkgSubImgDto.setSubImg01(subImg01);
      pkgSubImgDto.setSubImg02(subImg02);
      pkgSubImgDto.setSubImg03(subImg03);
      pkgSubImgDto.setSubImg04(subImg04);
      pkgSubImgDto.setSubImg05(subImg05);
      pkgSubImgDto.setSubImg06(subImg06);
      pkgSubImgDto.setSubImg07(subImg07);
      pkgSubImgDto.setSubImg08(subImg08);

      try {
        Files.write(Paths.get(uploadFolder + subImg01), pkgSubImgDto.getOriginSubImg01().getBytes());
        Files.write(Paths.get(uploadFolder + subImg02), pkgSubImgDto.getOriginSubImg02().getBytes());
        Files.write(Paths.get(uploadFolder + subImg03), pkgSubImgDto.getOriginSubImg03().getBytes());
        Files.write(Paths.get(uploadFolder + subImg04), pkgSubImgDto.getOriginSubImg04().getBytes());
        Files.write(Paths.get(uploadFolder + subImg05), pkgSubImgDto.getOriginSubImg05().getBytes());
        Files.write(Paths.get(uploadFolder + subImg06), pkgSubImgDto.getOriginSubImg06().getBytes());
        Files.write(Paths.get(uploadFolder + subImg07), pkgSubImgDto.getOriginSubImg07().getBytes());
        Files.write(Paths.get(uploadFolder + subImg08), pkgSubImgDto.getOriginSubImg08().getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return adminDao.pkgAlterSubImgProcess(pkgSubImgDto);
    }

    public int pkgDeleteProcess(int pkgNo) {
      return adminDao.pkgDeleteProcess(pkgNo);
    }

/////////////////////////////////////////////////////////////////////
////관리자용 Reservation 관리 서비스
    public List<ReservDto> getAllReserv(int page, String searchTxt, String category, int requested) {
      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("page", page);
      hashMap.put("searchTxt", searchTxt);
      hashMap.put("category", category);
      hashMap.put("requested", requested);
      return adminDao.getAllReserv(hashMap);
    }

    public ReservDto getOneReserv(int reservNo) {
      return adminDao.getOneReserv(reservNo);
    }
    public int totalCountReserv() {
      return adminDao.totalCountReserv();
    }

    public int reservModifyProcess(ReservDto reservDto) {
        int selectedPkgNo = reservDto.getSelectedPkgNo();
        PkgDto pkgDto = reservDao.getOnePkg(selectedPkgNo);
        reservDto.setReservPkg(pkgDto.getPkgName());
        reservDto.setReservImg(pkgDto.getPkgMainRenamedPath());
        reservDto.setReservDepart(pkgDto.getPkgDepart());
        reservDto.setReservReturn(pkgDto.getPkgReturn());
      
      int result = adminDao.reservModifyProcess(reservDto);
      adminDao.updateReq(reservDto);
      memberService.updateUserAlert(reservDto.getCustomerNo(),22);
      return result;
    }

    public int reservDeleteProcess(int reservNo) {
      return adminDao.reservDeleteProcess(reservNo);
    }

    public MemberDto reservRequestView(int customerNo) {
      return memberDao.getMemberInfo(customerNo);
    }
    

    public RequestDto getOneRequest(int reqNo) {
      return reservDao.getOneRequest(reqNo);
    }

/////////////////////////////////////////////////////////////////////
////관리자용 Member 관리 서비스
    public List<MemberDto> getAllMember(int page, String searchTxt, String category) {
      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("page", page);
      hashMap.put("searchTxt", searchTxt);
      hashMap.put("category", category);
      return adminDao.getAllMember(hashMap);
    }
    
    public MemberDto getOneMember(int no) {
      return adminDao.getOneMember(no);
    }

    public int totalCountMember() {
      return adminDao.totalCountMember();
    }

    public int memberModifyProcess(MemberDto memberDto) {
      return adminDao.memberModifyProcess(memberDto);
    }

    public int memberDeleteProcess(int no) {
      return adminDao.memberDeleteProcess(no);
    }




    /////////////////////////////////////////////////////////////////////
    ////관리자용 Review 관리 서비스
    public List<ReviewDto> getAllReview(int page, String searchTxt, String category) {
      HashMap<String, Object> hashMap = new HashMap<>();
      hashMap.put("page", page);
      hashMap.put("searchTxt", searchTxt);
      hashMap.put("category", category);
      return adminDao.getAllReview(hashMap);
    }

    public ReviewDto getOneReview(int reservNo) {
      return adminDao.getOneReview(reservNo);
    }

    @Override
    public int reviewModifyProcess(ReviewDto reviewDto) {

      double ratingAVG = Math.round(((reviewDto.getRatingCE()+reviewDto.getRatingFA()+reviewDto.getRatingGS())/3.0)*10)/10.0;
      reviewDto.setRatingAVG(ratingAVG);
      reviewDto.setRatingAVGTxt(""+ratingAVG);
      int result = adminDao.reviewModifyProcess(reviewDto);
      
      PkgDto pkgDto = reservDao.reqGetPkg(reviewDto.getReservNo());
      double ratedGS = (double)((pkgDto.getRatedGS())*(pkgDto.getRatedCount())+reviewDto.getRatingGS()-reviewDto.getPreviousGS())/(pkgDto.getRatedCount());
      double ratedFA = (double)((pkgDto.getRatedFA())*(pkgDto.getRatedCount())+reviewDto.getRatingFA()-reviewDto.getPreviousFA())/(pkgDto.getRatedCount());
      double ratedCE = (double)((pkgDto.getRatedCE())*(pkgDto.getRatedCount())+reviewDto.getRatingCE()-reviewDto.getPreviousCE())/(pkgDto.getRatedCount());
      int ratedCount = (pkgDto.getRatedCount());
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
}
