package com.bb.voyage.service;

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
        return pkgDao.getTempRecoList(pkgDto);
    }

    @Override
    public List<PkgDto> getRecommendedList(HashMap hashMap) {
        return pkgDao.getRecommendedList(hashMap);
    }


    

}
