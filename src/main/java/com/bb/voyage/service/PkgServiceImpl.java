package com.bb.voyage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bb.voyage.dao.PkgDao;
import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReviewDto;

@Service
public class PkgServiceImpl implements PkgService {

    @Autowired
    PkgDao pkgDao;
    @Autowired
    AdminService adminService;

    public List<PkgDto> pkgKoreaList() {
        return pkgDao.pkgKoreaList();
    }

    public List<PkgDto> pkgJapanList() {
        return pkgDao.pkgJapanList();
    }

    public List<PkgDto> pkgChinaList() {
        return pkgDao.pkgChinaList();
    }

    public List<PkgDto> getDiscountedPkgList() {
        return pkgDao.getDiscountedPkgList();

    }

    public List<PkgDto> getRecommendedPkgList() {
        return pkgDao.getRecommendedPkgList();

    }

    public int pkgSetRating(PkgDto pkgDto) {
        pkgDto.setRatedAVGTxt(""+pkgDto.getRatedAVG());
        return pkgDao.pkgSetRating(pkgDto);
    }

    public List<ReviewDto> getReview(PkgDto pkgDto) {
        return pkgDao.getReview(pkgDto);
    }

    public ReviewDto getSelectedReview(int reviewNo) {
        return pkgDao.getSelectedReview(reviewNo);
    }

    public List<PkgDto> getTempRecoList(PkgDto pkgDto) {
        List<PkgDto> pkgList = pkgDao.getTempRecoList(pkgDto);
        ArrayList<String> randomList = new ArrayList<>();
        for(int i=0;i<8;i++){
        randomList.clear();
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg01());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg02());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg03());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg04());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg05());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg06());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg07());
        randomList.add(adminService.getOnePkgSubImg(pkgList.get(i).getPkgNo()).getSubImg08());
        pkgList.get(i).setPkgMainRenamedPath(randomList.get((int)Math.round(Math.random()*7)));
        }
        return pkgList;
    }

    public List<PkgDto> getRecommendedList(HashMap<String,Object> hashMap) {
        return pkgDao.getRecommendedList(hashMap);
    }

}
