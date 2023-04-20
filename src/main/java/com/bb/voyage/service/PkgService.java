package com.bb.voyage.service;

import java.util.HashMap;
import java.util.List;

import com.bb.voyage.dto.PkgDto;
import com.bb.voyage.dto.ReviewDto;

public interface PkgService {
    public List<PkgDto> pkgKoreaList();
    public List<PkgDto> pkgJapanList();
    public List<PkgDto> pkgChinaList();
    public List<PkgDto> getDiscountedPkgList();
    public List<PkgDto> getRecommendedPkgList();
    public int pkgSetRating(PkgDto pkgDto);
    public List<ReviewDto> getReview(PkgDto pkgDto);
    public ReviewDto getSelectedReview(int reviewNo);
    public List<PkgDto> getTempRecoList(PkgDto pkgDto);
    public List<PkgDto> getRecommendedList(HashMap<String,Object> hashMap);

}
